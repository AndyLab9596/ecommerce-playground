package com.andy.ecommerce.services.impl;

import com.andy.ecommerce.dtos.reponse.GetAllUsersResponseDto;
import com.andy.ecommerce.mappers.UserMapper;
import com.andy.ecommerce.repositories.UserRepository;
import com.andy.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<GetAllUsersResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toGetAllUsersResponseDto).toList();
    }
}
