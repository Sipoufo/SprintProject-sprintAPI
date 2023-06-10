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
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long deviceTypeId;
    public String name;
}
