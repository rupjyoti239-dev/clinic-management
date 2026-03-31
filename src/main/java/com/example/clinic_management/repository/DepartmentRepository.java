package com.example.clinic_management.repository;

import com.example.clinic_management.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByName(String name);
    Boolean existsByName(String name);
    Page<Department> findByStatus(Boolean status, Pageable pageable);
    @Query("Select d From Department d Where d.status=true")
    Page<Department> findActiveDepartments(Pageable pageable);
}
