package com.example.hostelmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hostelmanagement.entity.Room;
import com.example.hostelmanagement.service.RoomService;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Create Room
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room savedRoom = roomService.addRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    // Get All Rooms
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    // Get Room By ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {

        Optional<Room> room = roomService.getRoomById(id);

        if (room.isPresent()) {
            return ResponseEntity.ok(room.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Update Room
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable Long id,
            @RequestBody Room roomDetails) {

        Optional<Room> updatedRoom =
                roomService.updateRoom(id, roomDetails);

        if (updatedRoom.isPresent()) {
            return ResponseEntity.ok(updatedRoom.get());
        }

        return ResponseEntity.notFound().build();
    }

    // Delete Room
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {

        boolean deleted = roomService.deleteRoom(id);

        if (deleted) {
            return ResponseEntity.ok("Room deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Room not found");
    }
}