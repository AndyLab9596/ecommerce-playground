package com.andy.ecommerce.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequestDto {
    @NotNull(message = "REQUIRED_FIELD")
    private String name;
}
