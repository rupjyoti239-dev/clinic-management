package com.example.clinic_management.controller.department;

import com.example.clinic_management.dto.department.DepartmentRequest;
import com.example.clinic_management.dto.department.DepartmentResponse;
import com.example.clinic_management.dto.department.DepartmentUpdate;
import com.example.clinic_management.service.department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/department")
public class DepartmentAdminController {
    private final DepartmentService departmentService;
    public DepartmentAdminController(DepartmentService departmentService)
    {
        this.departmentService=departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest departmentRequest)
    {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        return new ResponseEntity<>(departmentResponse, HttpStatus.CREATED);
    }
    @PatchMapping("/{deptId}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@Valid @RequestBody DepartmentUpdate departmentUpdate,
                                                               @PathVariable Long deptId)
    {
        DepartmentResponse departmentResponse = departmentService.updateDepartment(departmentUpdate,deptId);
        return new ResponseEntity<>(departmentResponse,HttpStatus.OK);
    }


    @PatchMapping("/{deptId}/status")
    public  ResponseEntity<DepartmentResponse> updateDepartmentStatus(@PathVariable Long deptId,@RequestParam Boolean status)
    {
        DepartmentResponse departmentResponse = departmentService.updateDepartmentStatus(deptId,status);
        return new ResponseEntity<>(departmentResponse,HttpStatus.OK);
    }
}
