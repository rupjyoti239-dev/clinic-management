package com.example.clinic_management.service.department.impl;

import com.example.clinic_management.dto.department.DepartmentRequest;
import com.example.clinic_management.dto.department.DepartmentResponse;
import com.example.clinic_management.dto.department.DepartmentUpdate;
import com.example.clinic_management.entity.Department;
import com.example.clinic_management.exception.ResourceAlreadyExistException;
import com.example.clinic_management.exception.ResourceNotFoundException;
import com.example.clinic_management.repository.DepartmentRepository;
import com.example.clinic_management.service.department.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository,
            ModelMapper modelMapper
    )
    {
        this.departmentRepository=departmentRepository;
        this.modelMapper=modelMapper;
    }



    @Transactional
    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        if(departmentRepository.existsByName(departmentRequest.getName())){
            throw new ResourceAlreadyExistException("Department already exist");
        }

        Department department = modelMapper.map(departmentRequest,Department.class);
        Department newDepartment = departmentRepository.save(department);
        return  modelMapper.map(newDepartment,DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse getDepartment(Long deptId) {
        Department department = departmentRepository.findById(deptId).orElseThrow(
                ()-> new ResourceNotFoundException("Department Not Found")
        );
        return modelMapper.map(department,DepartmentResponse.class);
    }

    @Transactional
    @Override
    public DepartmentResponse updateDepartment(DepartmentUpdate departmentUpdate, Long deptId) {
        Department department = departmentRepository.findById(deptId).orElseThrow(
                ()-> new ResourceNotFoundException("Department Not Found")
        );
        if(departmentUpdate.getName()!=null)
        {
            department.setName(departmentUpdate.getName());
        }
        if(departmentUpdate.getDescription()!=null)
        {
            department.setDescription(departmentUpdate.getDescription());
        }
        Department updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment,DepartmentResponse.class);


    }

    @Transactional
    @Override
    public DepartmentResponse updateDepartmentStatus(Long deptId, Boolean status) {
        Department department = departmentRepository.findById(deptId).orElseThrow(
                ()-> new ResourceNotFoundException("Department Not Found")
        );

        department.setStatus(status);
        Department updatedDepartment = departmentRepository.save(department);
        return modelMapper.map(updatedDepartment,DepartmentResponse.class);

    }



    @Override
    public Page<DepartmentResponse> departmentList(Pageable pageable) {
        return null;
    }
}
