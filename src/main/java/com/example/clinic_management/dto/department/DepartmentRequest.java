package com.example.clinic_management.dto.department;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentRequest {

    @NotBlank(message = "Department name cannot be blank")
    private String name;
    @NotBlank(message = "Department name cannot be blank")
    private String description;
}
