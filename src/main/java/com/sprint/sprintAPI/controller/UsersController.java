package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Users;
import com.sprint.sprintAPI.error.UserException;
import com.sprint.sprintAPI.service.UsersService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    private MqttClient mqttClient = null;
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @PostMapping("")
    @RolesAllowed({"ROLE_ADMIN"})
    public Users saveUser (@RequestBody Users deviceType) {
        return usersService.saveUser(deviceType);
    }

    @GetMapping("")
    public List<Users> fetchUsersList () {
        return usersService.fetchUsersList();
    }

    @GetMapping("/{id}")
    public Users fetchUserById (@PathVariable("id") Long Id) throws UserException {
        return usersService.fetchUserById(Id);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public Boolean deleteUser (@PathVariable("id") Long Id) {
        return usersService.deleteUSerById(Id);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public Users updateUser (@PathVariable("id") Long Id, @RequestBody Users metric) {
        return usersService.updateUserById(Id, metric);
    }

    @GetMapping("/message/{message}")
    public boolean sendCommand (@PathVariable("message") String message) throws UserException {
        try {
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());
            mqttmessage.setQos(1);
            this.mqttClient.publish("YvanElpCinArtYv", mqttmessage);
        } catch (MqttException me) {
            logger.error("ERROR", me);
        }
        return true;
    }
}
