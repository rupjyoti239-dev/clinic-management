package com.example.clinic_management.repository;

import com.example.clinic_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByMobile(String mobile);
}
