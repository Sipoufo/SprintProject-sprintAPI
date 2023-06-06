package com.sprint.sprintAPI.entity.formats;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MetricFormat {
    public String metricDate;
    public double metricValue;
    public Long deviceId;
}
