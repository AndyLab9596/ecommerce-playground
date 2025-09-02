package com.andy.ecommerce.controllers;

import com.andy.ecommerce.dtos.reponse.*;
import com.andy.ecommerce.dtos.request.CreateCategoryRequestDto;
import com.andy.ecommerce.dtos.request.UpdateCategoryRequestDto;
import com.andy.ecommerce.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all")
    ApiResponse<List<GetAllCategoriesResponseDto>> getAllCategories() {
        return ApiResponse.<List<GetAllCategoriesResponseDto>>builder().result(
                categoryService.getAllCategories()
        ).build();
    }

    @GetMapping("/get-category-by-id/{categoryId}")
    ApiResponse<GetCategoryByIdResponseDto> getCategoryById(
            @PathVariable Long categoryId
    ) {
        return ApiResponse.<GetCategoryByIdResponseDto>builder().result(
                categoryService.getCategoryById(categoryId)
        ).build();
    }
}
