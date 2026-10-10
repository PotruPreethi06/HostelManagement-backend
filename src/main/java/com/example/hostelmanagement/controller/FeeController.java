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

import com.example.hostelmanagement.entity.Fee;
import com.example.hostelmanagement.service.FeeService;

@RestController
@RequestMapping("/fees")
public class FeeController {

    @Autowired
    private FeeService feeService;

    // Create Fee
    @PostMapping
    public ResponseEntity<Fee> createFee(
            @RequestParam Long studentId,
            @RequestBody Fee fee) {

        Fee savedFee = feeService.createFee(studentId, fee);

        return new ResponseEntity<>(savedFee, HttpStatus.CREATED);
    }

    // Get all Fees
    @GetMapping
    public ResponseEntity<List<Fee>> getAllFees() {

        return ResponseEntity.ok(feeService.getAllFees());
    }

    // Get Fee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Fee> getFeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(feeService.getFeeById(id));
    }

    // Get Fees by Student ID
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Fee>> getFeesByStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                feeService.getFeesByStudent(studentId)
        );
    }

    // Get Fees by Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Fee>> getFeesByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                feeService.getFeesByStatus(status)
        );
    }

    // Update Fee
    @PutMapping("/{id}")
    public ResponseEntity<Fee> updateFee(
            @PathVariable Long id,
            @RequestBody Fee fee) {

        return ResponseEntity.ok(
                feeService.updateFee(id, fee)
        );
    }

    // Delete Fee
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFee(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                feeService.deleteFee(id)
        );
    }
}