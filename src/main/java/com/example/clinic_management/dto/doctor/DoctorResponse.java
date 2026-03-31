package com.example.clinic_management.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private Long id;

    private String name;
    private String email;
    private String mobile;

    private String specialization;
    private Integer experience;

    private Long departmentId;
    private String departmentName;
}
