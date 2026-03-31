package com.example.clinic_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends Audit{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String specialization;
   private Integer experience;

   @OneToOne
   @JoinColumn(name = "user_id",unique = true,nullable = false)
   @JsonIgnore
    private User user;


   @ManyToOne
   @JoinColumn(name = "dept_id",nullable = false)
    private Department department;
}
