package com.example.hostelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hostelmanagement.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}