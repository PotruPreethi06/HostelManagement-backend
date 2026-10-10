package com.example.hostelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Complaint;
import com.example.hostelmanagement.entity.Student;
import com.example.hostelmanagement.repository.ComplaintRepository;
import com.example.hostelmanagement.repository.StudentRepository;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Create Complaint
    public Complaint createComplaint(Long studentId, Complaint complaint) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        complaint.setStudent(student);

        return complaintRepository.save(complaint);
    }

    // Get all complaints
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // Get complaint by ID
    public Complaint getComplaintById(Long id) {

        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    // Get complaints by student
    public List<Complaint> getComplaintsByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return complaintRepository.findByStudent(student);
    }

    // Get complaints by status
    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }

    // Update complaint
    public Complaint updateComplaint(Long id, Complaint updatedComplaint) {

        Complaint existingComplaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        existingComplaint.setTitle(updatedComplaint.getTitle());
        existingComplaint.setDescription(updatedComplaint.getDescription());
        existingComplaint.setCategory(updatedComplaint.getCategory());
        existingComplaint.setComplaintDate(updatedComplaint.getComplaintDate());
        existingComplaint.setStatus(updatedComplaint.getStatus());

        return complaintRepository.save(existingComplaint);
    }

    // Delete complaint
    public String deleteComplaint(Long id) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaintRepository.delete(complaint);

        return "Complaint deleted successfully";
    }
}