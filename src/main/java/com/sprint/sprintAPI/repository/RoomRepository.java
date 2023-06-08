package com.sprint.sprintAPI.repository;

import com.sprint.sprintAPI.entity.Room;
import com.sprint.sprintAPI.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
