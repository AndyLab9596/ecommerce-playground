package com.andy.ecommerce.services;

import com.andy.ecommerce.dtos.reponse.GetAllUsersResponseDto;

import java.util.List;

public interface UserService {
    List<GetAllUsersResponseDto> getAllUsers();
}
