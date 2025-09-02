package com.andy.ecommerce.controllers;

import com.andy.ecommerce.dtos.reponse.ApiResponse;
import com.andy.ecommerce.dtos.reponse.CreateCategoryResponseDto;
import com.andy.ecommerce.dtos.reponse.UpdateEntityResponseDto;
import com.andy.ecommerce.dtos.request.CreateCategoryRequestDto;
import com.andy.ecommerce.dtos.request.UpdateCategoryRequestDto;
import com.andy.ecommerce.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    ApiResponse<CreateCategoryResponseDto> createCategory(@RequestBody @Valid CreateCategoryRequestDto createCategoryRequestDto) {
        return ApiResponse.<CreateCategoryResponseDto>builder().result(categoryService.createCategory(createCategoryRequestDto)).build();
    }

    @PostMapping("/update/{categoryId}")
    ApiResponse<UpdateEntityResponseDto> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid UpdateCategoryRequestDto updateCategoryRequestDto
    ) {
        return ApiResponse.<UpdateEntityResponseDto>builder().result(
                categoryService.updateCategory(categoryId, updateCategoryRequestDto)
        ).build();
    }
}
