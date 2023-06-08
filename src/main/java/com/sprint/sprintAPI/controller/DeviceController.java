package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.*;
import com.sprint.sprintAPI.entity.formats.DeviceFormat;
import com.sprint.sprintAPI.error.DeviceNotFoundException;
import com.sprint.sprintAPI.service.DeviceService;
import com.sprint.sprintAPI.service.DeviceTypeService;
import com.sprint.sprintAPI.service.RoomService;
import com.sprint.sprintAPI.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceTypeService deviceTypeService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private RoomService roomService;

    @PostMapping("")
    @RolesAllowed({"ROLE_ADMIN"})
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
    @RolesAllowed({"ROLE_ADMIN"})
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
    @RolesAllowed({"ROLE_ADMIN"})
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

    @GetMapping("/room/{id}")
    public List<Device> fetchDeviceByRoom (@PathVariable("id") Long roomId) {
        Room room = roomService.fetchRoomById(roomId);
        return deviceService.fetchDeviceByRoom(room);
    }
}
