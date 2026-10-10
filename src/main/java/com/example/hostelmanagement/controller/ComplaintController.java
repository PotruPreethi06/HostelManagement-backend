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

import com.example.hostelmanagement.entity.Complaint;
import com.example.hostelmanagement.service.ComplaintService;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    // Create Complaint
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(
            @RequestParam Long studentId,
            @RequestBody Complaint complaint) {

        Complaint savedComplaint =
                complaintService.createComplaint(studentId, complaint);

        return new ResponseEntity<>(savedComplaint, HttpStatus.CREATED);
    }

    // Get All Complaints
    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {

        return ResponseEntity.ok(
                complaintService.getAllComplaints()
        );
    }

    // Get Complaint By ID
    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                complaintService.getComplaintById(id)
        );
    }

    // Get Complaints By Student ID
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Complaint>> getComplaintsByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                complaintService.getComplaintsByStudent(studentId)
        );
    }

    // Get Complaints By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Complaint>> getComplaintsByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                complaintService.getComplaintsByStatus(status)
        );
    }

    // Update Complaint
    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(
            @PathVariable Long id,
            @RequestBody Complaint complaint) {

        return ResponseEntity.ok(
                complaintService.updateComplaint(id, complaint)
        );
    }

    // Delete Complaint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComplaint(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                complaintService.deleteComplaint(id)
        );
    }
}