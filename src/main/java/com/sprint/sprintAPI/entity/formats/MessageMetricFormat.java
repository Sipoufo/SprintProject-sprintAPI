package com.sprint.sprintAPI.entity.formats;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessageMetricFormat {
    public int device_representation;
    public int value;
    public String unit;
}