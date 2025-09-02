package com.andy.ecommerce.services.impl;

import com.andy.ecommerce.dtos.reponse.CreateCategoryResponseDto;
import com.andy.ecommerce.dtos.reponse.GetAllCategoriesResponseDto;
import com.andy.ecommerce.dtos.reponse.GetCategoryByIdResponseDto;
import com.andy.ecommerce.dtos.reponse.UpdateEntityResponseDto;
import com.andy.ecommerce.dtos.request.CreateCategoryRequestDto;
import com.andy.ecommerce.dtos.request.UpdateCategoryRequestDto;
import com.andy.ecommerce.entities.Category;
import com.andy.ecommerce.exceptions.AppException;
import com.andy.ecommerce.exceptions.ErrorCode;
import com.andy.ecommerce.mappers.CategoryMapper;
import com.andy.ecommerce.repositories.CategoryRepository;
import com.andy.ecommerce.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public CreateCategoryResponseDto createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = new Category();
        category.setName(createCategoryRequestDto.getName());
        categoryRepository.save(category);
        return CreateCategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateEntityResponseDto updateCategory(Long categoryId, UpdateCategoryRequestDto updateCategoryRequestDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setName(updateCategoryRequestDto.getName());
        categoryRepository.save(category);
        return UpdateEntityResponseDto.builder().id(category.getId()).build();
    }

    @Override
    public List<GetAllCategoriesResponseDto> getAllCategories() {
        List<GetAllCategoriesResponseDto> categories = categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toGetAllCategoriesResponseDto)
                .toList();
        return categories;
    }

    @Override
    public GetCategoryByIdResponseDto getCategoryById(Long categoryId) {
        var foundCategory = categoryRepository
                .findById(categoryId)
                .map(categoryMapper::toGetCategoryByIdResponseDto)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        return foundCategory;
    }
}
