package com.example.hostelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
