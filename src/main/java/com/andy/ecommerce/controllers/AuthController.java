package com.andy.ecommerce.controllers;

import com.andy.ecommerce.dtos.reponse.ApiResponse;
import com.andy.ecommerce.dtos.reponse.AuthenticateResponseDto;
import com.andy.ecommerce.dtos.reponse.IntrospectResponseDto;
import com.andy.ecommerce.dtos.reponse.RegisterUserResponseDto;
import com.andy.ecommerce.dtos.request.AuthenticateRequestDto;
import com.andy.ecommerce.dtos.request.IntrospectRequestDto;
import com.andy.ecommerce.dtos.request.RegisterUserRequestDto;
import com.andy.ecommerce.mappers.UserMapper;
import com.andy.ecommerce.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
     private final AuthService authService;
     private final UserMapper userMapper;

     @PostMapping("/register")
    ApiResponse<RegisterUserResponseDto> registerUser(@RequestBody @Valid RegisterUserRequestDto registerUserRequestDto) {
         var userRegistered = authService.registerUser(registerUserRequestDto);
         RegisterUserResponseDto registerUserResponseDto = userMapper.toRegisterUserResponseDto(userRegistered);
         return ApiResponse.<RegisterUserResponseDto>builder().result(registerUserResponseDto).build();
     }

     @PostMapping("/authenticate")
    ApiResponse<AuthenticateResponseDto> authenticate(@RequestBody @Valid AuthenticateRequestDto authenticateRequestDto) {
         return ApiResponse.<AuthenticateResponseDto>builder().result(
                 authService.authenticate(authenticateRequestDto)
         ).build();
     }

     @PostMapping("/introspect")
    ApiResponse<IntrospectResponseDto> introspect(@RequestBody @Valid IntrospectRequestDto introspectRequestDto) {
         return ApiResponse.<IntrospectResponseDto>builder().result(authService.introspect(introspectRequestDto)).build();
     }
}
