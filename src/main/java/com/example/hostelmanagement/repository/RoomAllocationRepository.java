package com.example.hostelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.RoomAllocation;

public interface RoomAllocationRepository extends JpaRepository<RoomAllocation, Long> {

    List<RoomAllocation> findByStudentId(Long studentId);

    List<RoomAllocation> findByRoomId(Long roomId);

}