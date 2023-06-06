package com.sprint.sprintAPI.entity.formats;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DeviceFormat {
    public String name;
    public int representationId;
    public Long deviceTypeId;
    public Long usersId;
}
