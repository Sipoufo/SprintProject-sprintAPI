package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.*;
import com.sprint.sprintAPI.error.DeviceNotFoundException;
import com.sprint.sprintAPI.repository.DeviceRepository;
import com.sprint.sprintAPI.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device saveDevice(Device device) {
        System.out.println(device);
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> fetchDevicesList() {
        return deviceRepository.findAll();
    }

    @Override
    public Device fetchDeviceById(Long deviceId) throws DeviceNotFoundException {
        Optional<Device> device = deviceRepository.findById(deviceId);
        if (!device.isPresent()) {
            throw new DeviceNotFoundException("This device don't exist");
        }
        return device.get();
    }

    @Override
    public boolean deleteDeviceById(Long deviceId) {
        deviceRepository.deleteById(deviceId);
        return true;
    }

    @Override
    public Device updateDeviceById(Long deviceId, Device device) {
        Device deviceDB = deviceRepository.findById(deviceId).get();
        if (Objects.nonNull(device.getName()) || "".equalsIgnoreCase(device.getName()))
            deviceDB.setName(device.getName());
        if (Objects.nonNull(device.getDeviceType()))
            deviceDB.setDeviceType(device.getDeviceType());
        if (Objects.nonNull(device.getUsers()))
            deviceDB.setUsers(device.getUsers());
        return deviceRepository.save(deviceDB);
    }

    @Override
    public List<Device> fetchDeviceByDeviceType(DeviceType deviceType) {
        return deviceRepository.findByDeviceType(deviceType);
    }

    @Override
    public List<Device> fetchDeviceByUser(Users users) {
        return deviceRepository.findByUsers(users);
    }

    @Override
    public List<Device> fetchDeviceByRoom(Room room) {
        return deviceRepository.findByRoom(room);
    }
}
