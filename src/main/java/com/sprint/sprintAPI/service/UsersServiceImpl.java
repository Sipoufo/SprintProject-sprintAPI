package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Users;
import com.sprint.sprintAPI.error.UserException;
import com.sprint.sprintAPI.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    private UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Users saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @Override
    public List<Users> fetchUsersList() {
        return usersRepository.findAll();
    }

    @Override
    public Users fetchUserById(Long userId) throws UserException {
        Optional<Users> users = usersRepository.findById(userId);
        if (!users.isPresent()) {
            throw new UserException().userNotFound();
        }
        return users.get();
    }

    @Override
    public boolean deleteUSerById(Long userId) {
        usersRepository.deleteById(userId);
        return true;
    }

    @Override
    public Users updateUserById(Long userId, Users user) {
        Users userDB = usersRepository.findById(userId).get();
        if (Objects.nonNull(user.getName()) || "".equalsIgnoreCase(user.getName()))
            userDB.setName(user.name);
        if (Objects.nonNull(user.getEmail()) || "".equalsIgnoreCase(user.getEmail()))
            userDB.setEmail(user.getEmail());
        if (Objects.nonNull(user.getSource()) || "".equalsIgnoreCase(user.getSource()))
            userDB.setEmail(user.getSource());
        return usersRepository.save(userDB);
    }

    @Override
    public Users fetchByUserEmail(String email) {
        Optional<Users> users = usersRepository.findByEmail(email);
        if (!users.isPresent())
            throw new UsernameNotFoundException("User not found");
        return users.get();
    }
}
