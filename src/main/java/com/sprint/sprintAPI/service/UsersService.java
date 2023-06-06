package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Users;
import com.sprint.sprintAPI.error.UserException;

import java.util.List;

public interface UsersService {
    public Users saveUser(Users user);
    public List<Users> fetchUsersList();
    public Users fetchUserById(Long userId) throws UserException;
    public boolean deleteUSerById(Long userId);
    public Users updateUserById(Long userId, Users user);
    public Users fetchByUserEmail(String email);
}
