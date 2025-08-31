package com.andy.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserAddressRequestDto {
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
