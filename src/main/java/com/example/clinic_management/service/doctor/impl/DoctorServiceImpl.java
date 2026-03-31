package com.example.clinic_management.service.doctor.impl;

import com.example.clinic_management.dto.doctor.DoctorRequest;
import com.example.clinic_management.dto.doctor.DoctorResponse;
import com.example.clinic_management.entity.Department;
import com.example.clinic_management.entity.Doctor;
import com.example.clinic_management.entity.Role;
import com.example.clinic_management.entity.User;
import com.example.clinic_management.exception.ResourceAlreadyExistException;
import com.example.clinic_management.exception.ResourceNotFoundException;
import com.example.clinic_management.repository.DepartmentRepository;
import com.example.clinic_management.repository.DoctorRepository;
import com.example.clinic_management.repository.UserRepository;
import com.example.clinic_management.service.doctor.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
@Service
public class DoctorServiceImpl implements DoctorService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public DoctorServiceImpl(
            UserRepository userRepository,
            DoctorRepository doctorRepository,
            DepartmentRepository departmentRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder
                             )
    {
        this.userRepository=userRepository;
        this.doctorRepository=doctorRepository;
        this.departmentRepository=departmentRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional
    @Override
    public DoctorResponse createDoctor(DoctorRequest doctorRequest) {

       if(userRepository.existsByEmail(doctorRequest.getEmail()))
       {
           throw new ResourceAlreadyExistException("email already exist");
       }
       if(userRepository.existsByMobile(doctorRequest.getMobile()))
       {
           throw new ResourceAlreadyExistException("Mobile already exist");
       }

        User user = new User();
       user.setName(doctorRequest.getName());
       user.setEmail(doctorRequest.getEmail());
       user.setMobile(doctorRequest.getMobile());
       user.setPassword(passwordEncoder.encode(doctorRequest.getPassword()));
       user.setRole(Role.DOCTOR);
       User savedUser = userRepository.save(user);

        Department department = departmentRepository.findById(doctorRequest.getDepartmentId()).orElseThrow(
                ()-> new ResourceNotFoundException("Department not found")
        );

        Doctor doctor = new Doctor();
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setSpecialization(doctorRequest.getSpecialization());
        doctor.setDepartment(department);
        doctor.setUser(user);

        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorResponse response = new DoctorResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setMobile(savedUser.getMobile());


        response.setSpecialization(savedDoctor.getSpecialization());
        response.setExperience(savedDoctor.getExperience());
        response.setDepartmentId(department.getId());
        response.setDepartmentName(department.getName());


        return response;


    }

    @Override
    public DoctorResponse getDoctor(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Doctor doctor = user.getDoctor();
        if (doctor == null) {
            throw new RuntimeException("Doctor profile not found");
        }

        DoctorResponse res = modelMapper.map(doctor, DoctorResponse.class);

        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setMobile(user.getMobile());
        if (doctor.getDepartment() != null) {
            res.setDepartmentId(doctor.getDepartment().getId());
            res.setDepartmentName(doctor.getDepartment().getName());
        }
        return res;
    }

    @Override
    public DoctorResponse updateDoctor(DoctorRequest doctorRequest, Long userId) {
        return null;
    }
}
