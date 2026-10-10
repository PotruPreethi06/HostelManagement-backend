package com.example.hostelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Fee;
import com.example.hostelmanagement.entity.Student;
import com.example.hostelmanagement.repository.FeeRepository;
import com.example.hostelmanagement.repository.StudentRepository;

@Service
public class FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Create Fee
    public Fee createFee(Long studentId, Fee fee) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        fee.setStudent(student);

        return feeRepository.save(fee);
    }

    // Get all fees
    public List<Fee> getAllFees() {
        return feeRepository.findAll();
    }

    // Get fee by ID
    public Fee getFeeById(Long id) {

        return feeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee not found"));
    }

    // Get fees by student
    public List<Fee> getFeesByStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return feeRepository.findByStudent(student);
    }

    // Get fees by status
    public List<Fee> getFeesByStatus(String status) {
        return feeRepository.findByStatus(status);
    }

    // Update Fee
    public Fee updateFee(Long id, Fee updatedFee) {

        Fee existingFee = feeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee not found"));

        existingFee.setAmount(updatedFee.getAmount());
        existingFee.setMonth(updatedFee.getMonth());
        existingFee.setPaymentDate(updatedFee.getPaymentDate());
        existingFee.setStatus(updatedFee.getStatus());

        return feeRepository.save(existingFee);
    }

    // Delete Fee
    public String deleteFee(Long id) {

        Fee fee = feeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee not found"));

        feeRepository.delete(fee);

        return "Fee deleted successfully";
    }
}