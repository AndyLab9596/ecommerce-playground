package com.andy.ecommerce.dtos.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryByIdResponseDto {
    private Long id;
    private String name;
    private LocalDateTime updatedAt;
    private List<GetCategoryByIdProductsResponseDto> productList;
}
