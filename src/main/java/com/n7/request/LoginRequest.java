package com.n7.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotNull(message = "Username can not empty")
    @Min(value = 6,message = "Length of username must longer 6")
    private String username;
    @NotNull(message = "Password can not empty")
    @Min(value = 6,message = "Length of password must longer 6")
    private String password;
}
