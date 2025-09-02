package com.andy.ecommerce.mappers;

import com.andy.ecommerce.dtos.reponse.GetAllCategoriesResponseDto;
import com.andy.ecommerce.dtos.reponse.GetCategoryByIdProductsResponseDto;
import com.andy.ecommerce.dtos.reponse.GetCategoryByIdResponseDto;
import com.andy.ecommerce.entities.Category;
import com.andy.ecommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(
            target = "numberOfProduct",
            expression = "java(mapNumberOfProduct(category))"
    )
    GetAllCategoriesResponseDto toGetAllCategoriesResponseDto(Category category);

    default Integer mapNumberOfProduct(Category category) {
        return category.getProductList() != null ? category.getProductList().size() : 0;
    }

    GetCategoryByIdResponseDto toGetCategoryByIdResponseDto(Category category);
    GetCategoryByIdProductsResponseDto toGetCategoryByIdProductsResponseDto(Product product);
}
