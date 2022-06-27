package com.example.exam.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 20, message = "username must be between 3 and 20 chars")
    private String username;
    @Email(message = "email must be valid")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 3, max = 20, message = "password must be between 3 and 20 chars")
    private String password;
    @NotBlank(message = "password is required")
    @Size(min = 3, max = 20, message = "confirmedPassword must be between 3 and 20 chars")
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
