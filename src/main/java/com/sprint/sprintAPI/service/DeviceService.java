package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import com.sprint.sprintAPI.entity.Metric;
import com.sprint.sprintAPI.entity.Users;
import com.sprint.sprintAPI.error.DeviceNotFoundException;

import java.util.List;

public interface DeviceService {
    public Device saveDevice(Device device);
    public List<Device> fetchDevicesList();
    public Device fetchDeviceById(Long deviceId) throws DeviceNotFoundException;
    public boolean deleteDeviceById(Long deviceId);
    public Device updateDeviceById(Long deviceId, Device device);
    public List<Device> fetchDeviceByDeviceType(DeviceType deviceType);
    public List<Device> fetchDeviceByUser(Users users);
}
