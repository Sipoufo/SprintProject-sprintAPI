package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Metric;
import com.sprint.sprintAPI.error.MetricException;
import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MetricService {
    public Metric saveMetric(Metric metric);
    public List<Metric> fetchDevicesList();
    public Metric fetchMetricById(Long metricId) throws MetricException;
    public boolean deleteMetricById(Long metricId);
    public Metric updateMetricById(Long metricId, Metric metric);
    public List<Metric> fetchMetricByDevice(Device device);
}
