package com.example.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.Attendance;
import com.example.hostelmanagement.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudent(Student student);

    List<Attendance> findByStatus(String status);
}