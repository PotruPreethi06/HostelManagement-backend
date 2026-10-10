package com.example.hostelmanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Room;
import com.example.hostelmanagement.entity.RoomAllocation;
import com.example.hostelmanagement.entity.Student;
import com.example.hostelmanagement.repository.RoomAllocationRepository;
import com.example.hostelmanagement.repository.RoomRepository;
import com.example.hostelmanagement.repository.StudentRepository;

@Service
public class RoomAllocationService {

    @Autowired
    private RoomAllocationRepository allocationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Allocate Student to Room
    public RoomAllocation allocateRoom(Long studentId, Long roomId) {

        // Check whether student exists
        Optional<Student> studentOptional =
                studentRepository.findById(studentId);

        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        // Check whether room exists
        Optional<Room> roomOptional =
                roomRepository.findById(roomId);

        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room not found with ID: " + roomId);
        }

        Student student = studentOptional.get();
        Room room = roomOptional.get();

        // Check room capacity
        if (room.getOccupied() >= room.getCapacity()) {
            throw new RuntimeException("Room is full");
        }

        // Create allocation
        RoomAllocation allocation = new RoomAllocation();

        allocation.setStudent(student);
        allocation.setRoom(room);
        allocation.setAllocationDate(LocalDate.now());
        allocation.setStatus("ALLOCATED");

        // Increase occupied count
        room.setOccupied(room.getOccupied() + 1);

        // Update room status
        if (room.getOccupied() >= room.getCapacity()) {
            room.setStatus("FULL");
        } else {
            room.setStatus("AVAILABLE");
        }

        // Save room
        roomRepository.save(room);

        // Save allocation
        return allocationRepository.save(allocation);
    }

    // Get all allocations
    public List<RoomAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    // Get allocation by ID
    public Optional<RoomAllocation> getAllocationById(Long id) {
        return allocationRepository.findById(id);
    }

    // Get allocations by student
    public List<RoomAllocation> getAllocationsByStudent(Long studentId) {
        return allocationRepository.findByStudentId(studentId);
    }

    // Get allocations by room
    public List<RoomAllocation> getAllocationsByRoom(Long roomId) {
        return allocationRepository.findByRoomId(roomId);
    }

    // Delete allocation
    public boolean deleteAllocation(Long id) {

        Optional<RoomAllocation> allocationOptional =
                allocationRepository.findById(id);

        if (allocationOptional.isEmpty()) {
            return false;
        }

        RoomAllocation allocation = allocationOptional.get();

        Room room = allocation.getRoom();

        // Decrease occupied count
        if (room.getOccupied() > 0) {
            room.setOccupied(room.getOccupied() - 1);
        }

        // Update room status
        if (room.getOccupied() < room.getCapacity()) {
            room.setStatus("AVAILABLE");
        }

        roomRepository.save(room);

        allocationRepository.deleteById(id);

        return true;
    }
}