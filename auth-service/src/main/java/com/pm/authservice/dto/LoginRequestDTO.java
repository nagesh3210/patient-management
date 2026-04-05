package com.pm.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class LoginRequestDTO
{

    @NotBlank(message = "email is requierd")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8 , message ="password length is minimum 8 words")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
