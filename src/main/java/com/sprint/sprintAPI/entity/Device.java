package com.sprint.sprintAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long deviceId;
    public String name;
    @Column(unique = true)
    public int representationId;
    public String description;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "device_type_id",
            referencedColumnName = "deviceTypeId"
    )
    public DeviceType deviceType;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    public Users users;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "roomId"
    )
    public Room room;
}
