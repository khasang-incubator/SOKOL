package ru.sokol.dto.department;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateDepartmentRequest {

    @NotBlank
    private String name;

}
