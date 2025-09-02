package com.andy.ecommerce.dtos.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoriesResponseDto {
    private Long id;
    private String name;
    private LocalDateTime updatedAt;
    private Integer numberOfProduct;
}
