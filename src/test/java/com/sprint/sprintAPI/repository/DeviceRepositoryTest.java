package com.sprint.sprintAPI.repository;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.DeviceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DeviceRepositoryTest {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TestEntityManager entityManager;

    private Device device;

    @BeforeEach
    void setUp() {
    }

    @Test
    void fetchOneDeviceById() {
    }

    @Test
    void fetchMetricsByIdDevice() {
    }
}