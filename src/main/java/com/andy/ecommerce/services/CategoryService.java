package com.andy.ecommerce.services;

import com.andy.ecommerce.dtos.reponse.CreateCategoryResponseDto;
import com.andy.ecommerce.dtos.request.CreateCategoryRequestDto;

public interface CategoryService {
    CreateCategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto);
}
