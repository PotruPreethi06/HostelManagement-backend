package com.example.hostelmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Room;
import com.example.hostelmanagement.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Create Room
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get All Rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get Room By ID
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // Update Room
    public Optional<Room> updateRoom(Long id, Room roomDetails) {

        Optional<Room> optionalRoom = roomRepository.findById(id);

        if (optionalRoom.isPresent()) {

            Room room = optionalRoom.get();

            room.setRoomNumber(roomDetails.getRoomNumber());
            room.setBlock(roomDetails.getBlock());
            room.setFloor(roomDetails.getFloor());
            room.setCapacity(roomDetails.getCapacity());
            room.setOccupied(roomDetails.getOccupied());
            room.setType(roomDetails.getType());
            room.setStatus(roomDetails.getStatus());

            return Optional.of(roomRepository.save(room));
        }

        return Optional.empty();
    }

    // Delete Room
    public boolean deleteRoom(Long id) {

        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }

        return false;
    }
}