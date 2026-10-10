package com.example.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.Complaint;
import com.example.hostelmanagement.entity.Student;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByStudent(Student student);

    List<Complaint> findByStatus(String status);
}