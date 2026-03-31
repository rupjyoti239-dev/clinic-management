package com.example.clinic_management.service.doctor;

import com.example.clinic_management.dto.doctor.DoctorRequest;
import com.example.clinic_management.dto.doctor.DoctorResponse;

public interface DoctorService {
    public DoctorResponse createDoctor(DoctorRequest doctorRequest);
    public DoctorResponse getDoctor(Long userId);
    public DoctorResponse updateDoctor(DoctorRequest doctorRequest,Long userId);
}
