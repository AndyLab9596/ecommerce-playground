package com.andy.ecommerce.services;

import com.andy.ecommerce.dtos.request.RegisterUserRequestDto;
import com.andy.ecommerce.entities.User;

public interface AuthService {
    User registerUser(RegisterUserRequestDto registerUserRequestDto);
}
