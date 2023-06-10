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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long roomId;
    @Column(unique = true, nullable = false)
    public String name;
}
