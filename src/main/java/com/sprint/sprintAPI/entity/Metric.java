package com.sprint.sprintAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long metricId;
    public String metricDate;
    public double metricValue;
    public String unit;
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "device_id",
            referencedColumnName = "deviceId"
    )
    public Device device;
}
