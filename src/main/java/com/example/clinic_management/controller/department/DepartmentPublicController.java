package com.example.clinic_management.controller.department;

import com.example.clinic_management.dto.department.DepartmentResponse;
import com.example.clinic_management.service.department.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentPublicController {

    private final DepartmentService departmentService;

    public DepartmentPublicController(DepartmentService departmentService)
    {
        this.departmentService=departmentService;
    }




    @GetMapping("/{deptId}")
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable Long deptId)
    {
        DepartmentResponse departmentResponse = departmentService.getDepartment(deptId);
        return new ResponseEntity<>(departmentResponse,HttpStatus.OK);
    }



}
