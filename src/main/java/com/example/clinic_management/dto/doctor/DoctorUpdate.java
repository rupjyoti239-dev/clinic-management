package com.example.clinic_management.dto.doctor;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdate {

    private String name;
    private String mobile;

    private String specialization;
    @Min(0)
    private Integer experience;

    private Long departmentId;

}
