package com.andy.ecommerce.services;

import com.andy.ecommerce.dtos.reponse.AuthenticateResponseDto;
import com.andy.ecommerce.dtos.request.AuthenticateRequestDto;
import com.andy.ecommerce.dtos.request.RegisterUserRequestDto;
import com.andy.ecommerce.entities.User;

public interface AuthService {
    User registerUser(RegisterUserRequestDto registerUserRequestDto);
    AuthenticateResponseDto authenticate(AuthenticateRequestDto authenticateRequestDto);
}
