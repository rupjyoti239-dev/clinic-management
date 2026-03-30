package com.example.clinic_management.service.department;

import com.example.clinic_management.dto.department.DepartmentRequest;
import com.example.clinic_management.dto.department.DepartmentResponse;
import com.example.clinic_management.dto.department.DepartmentUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    public DepartmentResponse getDepartment(Long deptId);
    public DepartmentResponse updateDepartment(DepartmentUpdate departmentUpdate,Long deptId);
    public DepartmentResponse updateDepartmentStatus(Long deptId,Boolean status);
    public Page<DepartmentResponse> departmentList(Pageable pageable);
}
