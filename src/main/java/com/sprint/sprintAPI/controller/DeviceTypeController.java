package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import com.sprint.sprintAPI.error.DeviceTypeException;
import com.sprint.sprintAPI.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/devicesTypes")
public class DeviceTypeController {
    @Autowired
    private DeviceTypeService deviceTypeService;

    @PostMapping("")
    @RolesAllowed({"ROLE_ADMIN"})
    public DeviceType saveDeviceType (@RequestBody DeviceType deviceType) {
        return deviceTypeService.saveDeviceType(deviceType);
    }

    @GetMapping("")
    public List<DeviceType> fetchDevicesTypesList () {
        return deviceTypeService.fetchDevicesTypesList();
    }

    @GetMapping("/{id}")
    public DeviceType fetchDevicesTypesById (@PathVariable("id") Long Id) throws DeviceTypeException {
        return deviceTypeService.fetchDeviceTypeById(Id);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public Boolean deleteDeviceType (@PathVariable("id") Long Id) {
        return deviceTypeService.deleteDeviceTypeById(Id);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public DeviceType updateDeviceType (@PathVariable("id") Long Id, @RequestBody DeviceType deviceType) {
        return deviceTypeService.updateDeviceTypeById(Id, deviceType);
    }
}
