package com.example.clinic_management.dto.department;

import com.example.clinic_management.dto.doctor.DoctorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean status;
  private List<DoctorResponse> doctorResponses;
}
