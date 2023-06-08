package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Room;
import com.sprint.sprintAPI.error.MetricException;
import com.sprint.sprintAPI.error.RoomNotFoundException;
import com.sprint.sprintAPI.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> fetchRoomsList() {
        return roomRepository.findAll();
    }

    @Override
    public Room fetchRoomById(Long roomId) throws MetricException {
        Optional<Room> room = roomRepository.findById(roomId);
        if (!room.isPresent()) {
            throw new RoomNotFoundException("This room don't exist");
        }
        return room.get();
    }

    @Override
    public boolean deleteRoomById(Long roomId) {
        roomRepository.deleteById(roomId);
        return true;
    }

    @Override
    public Room updateRoomById(Long roomId, Room room) {
        Room roomDB = roomRepository.findById(roomId).get();
        roomDB.setName(room.getName());
        return roomRepository.save(roomDB);
    }
}
