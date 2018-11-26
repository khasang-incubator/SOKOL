package ru.sokol.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;
}
