package com.example.hostelmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hostelmanagement.entity.RoomAllocation;
import com.example.hostelmanagement.service.RoomAllocationService;

@RestController
@RequestMapping("/allocations")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomAllocationController {

    @Autowired
    private RoomAllocationService allocationService;

    // Allocate Student to Room
    @PostMapping
    public ResponseEntity<?> allocateRoom(
            @RequestParam Long studentId,
            @RequestParam Long roomId) {

        try {
            RoomAllocation allocation =
                    allocationService.allocateRoom(studentId, roomId);

            return new ResponseEntity<>(allocation, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    // Get All Allocations
    @GetMapping
    public ResponseEntity<List<RoomAllocation>> getAllAllocations() {

        List<RoomAllocation> allocations =
                allocationService.getAllAllocations();

        return ResponseEntity.ok(allocations);
    }

    // Get Allocation By ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomAllocation> getAllocationById(
            @PathVariable Long id) {

        Optional<RoomAllocation> allocation =
                allocationService.getAllocationById(id);

        if (allocation.isPresent()) {
            return ResponseEntity.ok(allocation.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Get Allocations By Student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RoomAllocation>> getAllocationsByStudent(
            @PathVariable Long studentId) {

        List<RoomAllocation> allocations =
                allocationService.getAllocationsByStudent(studentId);

        return ResponseEntity.ok(allocations);
    }

    // Get Allocations By Room
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<RoomAllocation>> getAllocationsByRoom(
            @PathVariable Long roomId) {

        List<RoomAllocation> allocations =
                allocationService.getAllocationsByRoom(roomId);

        return ResponseEntity.ok(allocations);
    }

    // Delete Allocation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAllocation(
            @PathVariable Long id) {

        boolean deleted =
                allocationService.deleteAllocation(id);

        if (deleted) {
            return ResponseEntity.ok(
                    "Room allocation deleted successfully");
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Room allocation not found");
    }
}