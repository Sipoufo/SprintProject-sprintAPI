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
public class DeviceType {
    @Id
    @SequenceGenerator(
            name = "device_type_sequence",
            sequenceName = "device_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_type_sequence"
    )
    public Long deviceTypeId;
    public String name;
}
