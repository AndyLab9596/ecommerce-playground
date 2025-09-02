package com.andy.ecommerce.services;

import com.andy.ecommerce.dtos.reponse.GetAllUsersResponseDto;
import com.andy.ecommerce.dtos.reponse.GetMyInfoResponseDto;

import java.util.List;

public interface UserService {
    List<GetAllUsersResponseDto> getAllUsers();
    GetMyInfoResponseDto getMyInfo();
}
