package com.example.clinic_management.controller.doctor;

import com.example.clinic_management.dto.doctor.DoctorResponse;
import com.example.clinic_management.service.doctor.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/public/doctor")
@RestController
public class DoctorPublicController {

    private final DoctorService doctorService;

    public DoctorPublicController(DoctorService doctorService)
    {
        this.doctorService=doctorService;
    }



    @GetMapping("/{userId}")
        public ResponseEntity<DoctorResponse> getDoctor(@PathVariable Long userId)
    {
        DoctorResponse doctorResponse = doctorService.getDoctor(userId);
        return new ResponseEntity<>(doctorResponse, HttpStatus.OK);
    }
}
