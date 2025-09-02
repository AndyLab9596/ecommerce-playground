package com.andy.ecommerce.services.impl;

import com.andy.ecommerce.dtos.reponse.AuthenticateResponseDto;
import com.andy.ecommerce.dtos.reponse.IntrospectResponseDto;
import com.andy.ecommerce.dtos.request.AuthenticateRequestDto;
import com.andy.ecommerce.dtos.request.IntrospectRequestDto;
import com.andy.ecommerce.dtos.request.RegisterUserRequestDto;
import com.andy.ecommerce.entities.User;
import com.andy.ecommerce.enums.UserRole;
import com.andy.ecommerce.exceptions.AppException;
import com.andy.ecommerce.exceptions.ErrorCode;
import com.andy.ecommerce.mappers.UserMapper;
import com.andy.ecommerce.repositories.UserRepository;
import com.andy.ecommerce.services.AuthService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${jwt.signer-key}")
    protected String SIGNER_KEY;

    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    public User registerUser(RegisterUserRequestDto registerUserRequestDto) {
        User user = userMapper.fromRegisterUserRequestDto(registerUserRequestDto);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUserRole(UserRole.USER);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        if (user.getAddress() != null) {
            user.getAddress().setUser(user);
        }
        // TODO: Send e-mail to new user
        return userRepository.save(user);
    }

    @Override
    public AuthenticateResponseDto authenticate(AuthenticateRequestDto authenticateRequestDto) {
        // find user by email -> if user's existed
        var user = userRepository
                .findByEmail(authenticateRequestDto.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        // check matched password
        boolean authenticated = passwordEncoder().matches(authenticateRequestDto.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        // generate token
        var accessToken = generateToken(user);

        return AuthenticateResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .userRole(user.getUserRole())
                .accessToken(accessToken)
                .build();
    }

    @Override
    public IntrospectResponseDto introspect(IntrospectRequestDto introspectRequestDto) {
        var token = introspectRequestDto.getAccessToken();
        boolean isValid = true;
        try {
            verifyToken(token);
        } catch (AppException | JOSEException | ParseException e) {
            isValid = false;
        }
        return IntrospectResponseDto.builder().isValid(isValid).build();
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);
        return signedJWT;
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("andVy.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", "ROLE_" + user.getUserRole())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
