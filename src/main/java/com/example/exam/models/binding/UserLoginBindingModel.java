package com.example.exam.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {
    @NotBlank(message = "username is required")
    @Size(min = 3,  max = 20, message = "username must be between 3 and 20 symbols")
    private String username;
    @NotBlank(message = "password is required")
    @Size(min = 3, max = 20, message = "password must be at between 3 and 20 symbols")
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
