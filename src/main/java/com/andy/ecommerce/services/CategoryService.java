package com.andy.ecommerce.services;

import com.andy.ecommerce.dtos.reponse.CreateCategoryResponseDto;
import com.andy.ecommerce.dtos.reponse.GetAllCategoriesResponseDto;
import com.andy.ecommerce.dtos.reponse.UpdateEntityResponseDto;
import com.andy.ecommerce.dtos.request.CreateCategoryRequestDto;
import com.andy.ecommerce.dtos.request.UpdateCategoryRequestDto;

import java.util.List;

public interface CategoryService {
    CreateCategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto);
    UpdateEntityResponseDto updateCategory(Long categoryId, UpdateCategoryRequestDto updateCategoryRequestDto);
    List<GetAllCategoriesResponseDto> getAllCategories();
}
