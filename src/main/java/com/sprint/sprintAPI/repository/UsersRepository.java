package com.sprint.sprintAPI.repository;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import com.sprint.sprintAPI.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
