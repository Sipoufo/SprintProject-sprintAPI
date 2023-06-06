package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import com.sprint.sprintAPI.error.DeviceTypeException;
import com.sprint.sprintAPI.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceTypeServiceImp implements DeviceTypeService {
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    DeviceServiceImpl deviceService;

    @Override
    public DeviceType saveDeviceType(DeviceType deviceType) {
        return deviceTypeRepository.save(deviceType);
    }

    @Override
    public List<DeviceType> fetchDevicesTypesList() {
        return deviceTypeRepository.findAll();
    }

    @Override
    public DeviceType fetchDeviceTypeById(Long deviceTypeId) throws DeviceTypeException {
        Optional<DeviceType> deviceType = deviceTypeRepository.findById(deviceTypeId);
        if (!deviceType.isPresent()) {
            throw new DeviceTypeException().deviceTypeNotFound(deviceTypeId);
        }
        return deviceType.get();
    }

    @Override
    public boolean deleteDeviceTypeById(Long deviceTypeId) {
        deviceTypeRepository.deleteById(deviceTypeId);
        return true;
    }

    @Override
    public DeviceType updateDeviceTypeById(Long deviceTypeId, DeviceType deviceType) {
        DeviceType deviceTypeDB = deviceTypeRepository.findById(deviceTypeId).get();
        if (Objects.nonNull(deviceType.getName()) || "".equalsIgnoreCase(deviceType.getName()))
            deviceTypeDB.setName(deviceType.getName());
        return deviceTypeRepository.save(deviceTypeDB);
    }
}
