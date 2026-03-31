package com.example.clinic_management.dto.doctor;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

    @NotBlank(message = "name cannot be blank")
    private String name;
    @Email(message = "email must be a valid email")
    private String email;
    @NotBlank(message = "mobile cannot be blank")
    private String mobile;
    @NotBlank(message = "password cannot be blank")
    @Size(min = 6)
    private String password;

    @NotBlank(message = "specialization cannot be blank")
    private String specialization;
    @NotNull
    @Min(0)
    private Integer experience;

    @NotNull
    private Long departmentId;
}
