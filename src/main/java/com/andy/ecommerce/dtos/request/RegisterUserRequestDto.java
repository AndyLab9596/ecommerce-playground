package com.andy.ecommerce.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDto {
    @NotBlank(message = "INVALID_USER_NAME")
    private String name;

    @Email()
    private String email;

    @Size(min = 3, message = "INVALID_PASSWORD")
    private String password;

    @NotBlank(message = "User's phone number is required")
    private String phoneNumber;

    @Valid
    private RegisterUserAddressRequestDto address;
}
