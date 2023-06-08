package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Room;
import com.sprint.sprintAPI.error.MetricException;

import java.util.List;

public interface RoomService {
    public Room saveRoom(Room room);
    public List<Room> fetchRoomsList();
    public Room fetchRoomById(Long roomId) throws MetricException;
    public boolean deleteRoomById(Long roomId);
    public Room updateRoomById(Long roomId, Room room);
}
