package ru.sokol.dto.department;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class UpdateDepartmentRequest {

    @Positive
    private Integer id;

    @NotBlank
    private String name;

}
