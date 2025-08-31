package com.andy.ecommerce.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserAddressRequestDto {
    @NotBlank(message = "REQUIRED_FIELD")
    private String street;

    @NotBlank(message = "REQUIRED_FIELD")
    private String city;

    @NotBlank(message = "REQUIRED_FIELD")
    private String state;

    @NotBlank(message = "REQUIRED_FIELD")
    private String zipcode;

    @NotBlank(message = "REQUIRED_FIELD")
    private String country;
}
