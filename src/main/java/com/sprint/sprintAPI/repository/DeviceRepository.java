package com.sprint.sprintAPI.repository;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import com.sprint.sprintAPI.entity.Metric;
import com.sprint.sprintAPI.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    public List<Device> findByDeviceType(DeviceType deviceType);
    public List<Device> findByUsers(Users users);
    public List<Device> findByRoom(Users users);
}
