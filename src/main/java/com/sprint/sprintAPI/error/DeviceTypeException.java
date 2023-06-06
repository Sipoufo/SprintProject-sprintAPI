package com.sprint.sprintAPI.error;

public class DeviceTypeException extends RuntimeException {

    public DeviceTypeException() {
        super();
    }

    private DeviceTypeException(String message) {
        super(message);
    }

    public static DeviceTypeException deviceTypeNotFound(Long deviceTypeId) {
        return new DeviceTypeException("Type d'appareil introuvable avec l'ID : " + deviceTypeId);
    }

    public static DeviceTypeException invalidDeviceType(String message) {
        return new DeviceTypeException(message);
    }
    
}
