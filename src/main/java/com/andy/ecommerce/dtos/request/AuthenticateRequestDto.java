package com.andy.ecommerce.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequestDto {
    @NotBlank(message = "INVALID_USER_NAME")
    @Email()
    private String email;

    @Size(min = 3, message = "INVALID_PASSWORD")
    private String password;
}
