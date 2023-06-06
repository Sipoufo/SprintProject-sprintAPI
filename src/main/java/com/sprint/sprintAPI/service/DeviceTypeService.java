package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import com.sprint.sprintAPI.error.DeviceTypeException;

import java.util.List;

public interface DeviceTypeService {
    public DeviceType saveDeviceType(DeviceType deviceType);
    public List<DeviceType> fetchDevicesTypesList();
    public DeviceType fetchDeviceTypeById(Long deviceTypeId) throws DeviceTypeException;
    public boolean deleteDeviceTypeById(Long deviceTypeId);
    public DeviceType updateDeviceTypeById(Long deviceTypeId, DeviceType deviceType);
}
