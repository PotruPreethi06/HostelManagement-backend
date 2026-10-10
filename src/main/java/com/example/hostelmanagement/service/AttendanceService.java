package com.example.hostelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Attendance;
import com.example.hostelmanagement.entity.Student;
import com.example.hostelmanagement.repository.AttendanceRepository;
import com.example.hostelmanagement.repository.StudentRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Create Attendance
    public Attendance createAttendance(Long studentId, Attendance attendance) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        attendance.setStudent(student);

        return attendanceRepository.save(attendance);
    }

    // Get all Attendance
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Get Attendance by ID
    public Attendance getAttendanceById(Long id) {

        return attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }

    // Get Attendance by Student
    public List<Attendance> getAttendanceByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return attendanceRepository.findByStudent(student);
    }

    // Get Attendance by Status
    public List<Attendance> getAttendanceByStatus(String status) {
        return attendanceRepository.findByStatus(status);
    }

    // Update Attendance
    public Attendance updateAttendance(Long id, Attendance updatedAttendance) {

        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        existingAttendance.setAttendanceDate(
                updatedAttendance.getAttendanceDate()
        );

        existingAttendance.setStatus(
                updatedAttendance.getStatus()
        );

        return attendanceRepository.save(existingAttendance);
    }

    // Delete Attendance
    public String deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);

        return "Attendance deleted successfully";
    }
}