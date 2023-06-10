package com.sprint.sprintAPI.service;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Metric;
import com.sprint.sprintAPI.error.MetricException;
import com.sprint.sprintAPI.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MetricServiceImpl implements MetricService{
    @Autowired
    private MetricRepository metricRepository;
    @Override
    public Metric saveMetric(Metric metric) {
        return metricRepository.save(metric);
    }

    @Override
    public List<Metric> fetchDevicesList() {
        return metricRepository.findAll();
    }

    @Override
    public Metric fetchMetricById(Long metricId) throws MetricException {
        Optional<Metric> metric = metricRepository.findById(metricId);
        if (!metric.isPresent()) {
            throw new MetricException().metricNotFound();
        }
        return metric.get();
    }

    @Override
    public boolean deleteMetricById(Long metricId) {
        metricRepository.deleteById(metricId);
        return true;
    }

    @Override
    public Metric updateMetricById(Long metricId, Metric metric) {
        Metric metricDB = metricRepository.findById(metricId).get();
        if (Objects.nonNull(metric.getMetricValue())) {
            metricDB.setMetricValue(metric.getMetricValue());
        }
        if (Objects.nonNull(metric.getMetricDate()) || "".equalsIgnoreCase(metric.getMetricDate())) {
            metricDB.setMetricDate(metric.getMetricDate());
        }
        if (Objects.nonNull(metric.getDevice())) {
            metricDB.setDevice(metric.getDevice());
        }
        return metricRepository.save(metricDB);
    }

    @Override
    public List<Metric> fetchMetricByDevice(Device device) {
        return metricRepository.findByDevice(device);
    }

//    @Override
//    public Metric fetchLastMetricByDevice(Device device) {
//        return metricRepository.findByDevice(device).get(0);
//    }
}
