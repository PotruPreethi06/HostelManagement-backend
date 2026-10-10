package com.example.hostelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hostelmanagement.entity.Attendance;
import com.example.hostelmanagement.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Create Attendance
    @PostMapping
    public ResponseEntity<Attendance> createAttendance(
            @RequestParam Long studentId,
            @RequestBody Attendance attendance) {

        Attendance savedAttendance =
                attendanceService.createAttendance(studentId, attendance);

        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }

    // Get All Attendance
    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance()
        );
    }

    // Get Attendance By ID
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(id)
        );
    }

    // Get Attendance By Student ID
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendanceByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByStudent(studentId)
        );
    }

    // Get Attendance By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Attendance>> getAttendanceByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceByStatus(status)
        );
    }

    // Update Attendance
    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(
            @PathVariable Long id,
            @RequestBody Attendance attendance) {

        return ResponseEntity.ok(
                attendanceService.updateAttendance(id, attendance)
        );
    }

    // Delete Attendance
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.deleteAttendance(id)
        );
    }
}