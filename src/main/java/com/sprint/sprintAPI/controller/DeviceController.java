package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import com.sprint.sprintAPI.entity.Metric;
import com.sprint.sprintAPI.entity.Users;
import com.sprint.sprintAPI.entity.formats.DeviceFormat;
import com.sprint.sprintAPI.error.DeviceNotFoundException;
import com.sprint.sprintAPI.service.DeviceService;
import com.sprint.sprintAPI.service.DeviceTypeService;
import com.sprint.sprintAPI.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public Device saveDevice (@RequestBody DeviceFormat device) {
        DeviceType deviceType = deviceTypeService.fetchDeviceTypeById(device.getDeviceTypeId());
        Users user = usersService.fetchUserById(device.getUsersId());
        Device device1 = Device
                .builder()
                .name(device.getName())
                .deviceType(deviceType)
                .users(user)
                .representationId(device.getRepresentationId())
                .build();
        return deviceService.saveDevice(device1);
    }

    @GetMapping("")
    public List<Device> fetchDevicesList () {
        return deviceService.fetchDevicesList();
    }

    @GetMapping("/{id}")
    public Device fetchDeviceById (@PathVariable("id") Long deviceId) throws DeviceNotFoundException {
        return deviceService.fetchDeviceById(deviceId);
    }

    @PutMapping("/{id}")
    public Device updateDevice (@PathVariable("id") Long deviceId, @RequestBody DeviceFormat device) {
        DeviceType deviceType = deviceTypeService.fetchDeviceTypeById(device.getDeviceTypeId());
        Users user = usersService.fetchUserById(device.getUsersId());
        Device device1 = Device
                .builder()
                .name(device.getName())
                .deviceType(deviceType)
                .users(user)
                .representationId(device.getRepresentationId())
                .build();
        return deviceService.updateDeviceById(deviceId, device1);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteDevice (@PathVariable("id") Long deviceId) {
        return deviceService.deleteDeviceById(deviceId);
    }

    @GetMapping("/deviceType/{id}")
    public List<Device> fetchMetricsByIdDevice (@PathVariable("id") Long deviceId) {
        DeviceType deviceType = deviceTypeService.fetchDeviceTypeById(deviceId);
        return deviceService.fetchDeviceByDeviceType(deviceType);
    }

    @GetMapping("/user/{id}")
    public List<Device> fetchDeviceByUser (@PathVariable("id") Long userId) {
        Users user = usersService.fetchUserById(userId);
        return deviceService.fetchDeviceByUser(user);
    }
}
