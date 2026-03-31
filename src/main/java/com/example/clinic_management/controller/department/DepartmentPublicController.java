package com.example.clinic_management.controller.department;

import com.example.clinic_management.dto.department.DepartmentResponse;
import com.example.clinic_management.service.department.DepartmentService;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping
    public ResponseEntity<Page<DepartmentResponse>> departmentList(
            @RequestParam(required = false) Boolean status,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "name") String sortBy
    ) {

        // 🔥 Validation
        if (pageSize < 1) pageSize = 10;
        if (pageNo < 0) pageNo = 0;

        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<DepartmentResponse> response =
                departmentService.departmentList(status, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
