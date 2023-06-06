package com.sprint.sprintAPI.controller;

import com.sprint.sprintAPI.entity.Device;
import com.sprint.sprintAPI.entity.Metric;
import com.sprint.sprintAPI.entity.formats.MetricFormat;
import com.sprint.sprintAPI.error.MetricException;
import com.sprint.sprintAPI.service.DeviceService;
import com.sprint.sprintAPI.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/metric")
public class MetricController {
    @Autowired
    private MetricService metricService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("")
    public Metric saveMetric (@RequestBody MetricFormat metric) {
        Device device = deviceService.fetchDeviceById(metric.deviceId);
        Metric metric1 = Metric
                .builder()
                .metricValue((double) metric.getMetricValue())
                .metricDate(metric.getMetricDate())
                .device(device)
                .build();
        return metricService.saveMetric(metric1);
    }

    @GetMapping("")
    public List<Metric> fetchMetricsList () {
        return metricService.fetchDevicesList();
    }

    @GetMapping("/{id}")
    public Metric fetchMetricById (@PathVariable("id") Long Id) throws MetricException {
        return metricService.fetchMetricById(Id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteMetric (@PathVariable("id") Long Id) {
        return metricService.deleteMetricById(Id);
    }

    @PutMapping("/{id}")
    public Metric updateMetric (@PathVariable("id") Long Id, @RequestBody MetricFormat metric) {
        Device device = deviceService.fetchDeviceById(metric.deviceId);
        Metric metric1 = Metric
                .builder()
                .metricValue((double) metric.getMetricValue())
                .metricDate(metric.getMetricDate())
                .device(device)
                .build();
        return metricService.updateMetricById(Id, metric1);
    }

    @GetMapping("/device/{id}")
    public List<Metric> fetchMetricByDevice (@PathVariable("id") Long Id) {
        Device device = deviceService.fetchDeviceById(Id);
        return metricService.fetchMetricByDevice(device);
    }
}
