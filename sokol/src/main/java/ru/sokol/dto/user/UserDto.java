package ru.sokol.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UserDto {

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String fullName;
}
