package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.Room;
import com.sprint.sprintAPI.entity.formats.RoomFormat;
import com.sprint.sprintAPI.error.RoomNotFoundException;
import com.sprint.sprintAPI.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @PostMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public Room saveRoom (@RequestBody RoomFormat room) {
        Room room1 = Room
                .builder()
                .name(room.getName())
                .build();
        return roomService.saveRoom(room1);
    }

    @GetMapping
    public List<Room> fetchRoomsList () {
        return roomService.fetchRoomsList();
    }

    @GetMapping("/{id}")
    public Room fetchRoomById (@PathVariable("id") Long roomId) throws RoomNotFoundException {
        return roomService.fetchRoomById(roomId);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public Room updateRoom (@PathVariable("id") Long roomId, @RequestBody RoomFormat room) {
        Room room1 = Room
                .builder()
                .name(room.getName())
                .build();
        return roomService.updateRoomById(roomId, room1);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public Boolean deleteRoom (@PathVariable("id") Long roomId) {
        return roomService.deleteRoomById(roomId);
    }
}
