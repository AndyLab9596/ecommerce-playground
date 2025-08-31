package com.andy.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private RegisterUserAddressRequestDto address;
}
