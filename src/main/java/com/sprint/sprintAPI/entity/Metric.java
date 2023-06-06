package com.sprint.sprintAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Metric {
    @Id
    @SequenceGenerator(
            name = "metric_sequence",
            sequenceName = "metric_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "metric_sequence"
    )
    public Long metricId;
    public String metricDate;
    public double metricValue;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "device_id",
            referencedColumnName = "deviceId"
    )
    public Device device;
}
