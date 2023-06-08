package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Users;
import com.sprint.sprintAPI.error.UserException;
import com.sprint.sprintAPI.service.UsersService;
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
}
