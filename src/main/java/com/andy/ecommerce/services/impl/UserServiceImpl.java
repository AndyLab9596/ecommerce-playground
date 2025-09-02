package com.andy.ecommerce.services.impl;

import com.andy.ecommerce.dtos.reponse.GetAllUsersResponseDto;
import com.andy.ecommerce.dtos.reponse.GetMyInfoResponseDto;
import com.andy.ecommerce.entities.User;
import com.andy.ecommerce.exceptions.AppException;
import com.andy.ecommerce.exceptions.ErrorCode;
import com.andy.ecommerce.mappers.UserMapper;
import com.andy.ecommerce.repositories.UserRepository;
import com.andy.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<GetAllUsersResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toGetAllUsersResponseDto).toList();
    }

    @Override
    @PostAuthorize("returnObject.email == authentication.name")
    public GetMyInfoResponseDto getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toGetMyInfoResponseDto(user);
    }
}
