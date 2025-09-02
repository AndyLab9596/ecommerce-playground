package com.andy.ecommerce.dtos.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyInfoAddressResponseDto {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
