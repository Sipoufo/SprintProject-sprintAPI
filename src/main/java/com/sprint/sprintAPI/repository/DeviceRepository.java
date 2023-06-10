package com.sprint.sprintAPI.repository;

import com.sprint.sprintAPI.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    public List<Device> findByDeviceType(DeviceType deviceType);
    public List<Device> findByUsers(Users users);
    public List<Device> findByRoom(Room room);
    public Device findByRepresentationId(int representationId);
}
