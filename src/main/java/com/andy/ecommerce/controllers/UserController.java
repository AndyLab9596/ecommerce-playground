package com.andy.ecommerce.controllers;

import com.andy.ecommerce.dtos.reponse.ApiResponse;
import com.andy.ecommerce.dtos.reponse.GetAllUsersResponseDto;
import com.andy.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get-all")
    public ApiResponse<List<GetAllUsersResponseDto>> getAll() {
        return ApiResponse.<List<GetAllUsersResponseDto>>builder().result(userService.getAllUsers()).build();
    }
}
