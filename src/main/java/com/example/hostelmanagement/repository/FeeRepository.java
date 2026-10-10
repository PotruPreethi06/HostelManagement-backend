package com.example.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.Fee;
import com.example.hostelmanagement.entity.Student;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    List<Fee> findByStudent(Student student);

    List<Fee> findByStatus(String status);
}