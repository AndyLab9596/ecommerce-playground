package com.andy.ecommerce.dtos.reponse;

import com.andy.ecommerce.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private UserRole userRole;
    private RegisterUserAddressResponseDto address;
}
