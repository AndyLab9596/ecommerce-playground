package com.andy.ecommerce.services.impl;

import com.andy.ecommerce.dtos.request.RegisterUserRequestDto;
import com.andy.ecommerce.entities.User;
import com.andy.ecommerce.enums.UserRole;
import com.andy.ecommerce.exceptions.AppException;
import com.andy.ecommerce.exceptions.ErrorCode;
import com.andy.ecommerce.mappers.UserMapper;
import com.andy.ecommerce.repositories.UserRepository;
import com.andy.ecommerce.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(RegisterUserRequestDto registerUserRequestDto) {
        User user = userMapper.fromRegisterUserRequestDto(registerUserRequestDto);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUserRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getAddress() != null) {
            user.getAddress().setUser(user);
        }
        // TODO: Send e-mail to new user
        return userRepository.save(user);
    }
}
