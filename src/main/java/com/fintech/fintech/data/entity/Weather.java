package com.fintech.fintech.data.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private Long id;
    private String regionName;
    private Double temperature;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "regionName='" + regionName + '\'' + ", temperature=" + temperature;
    }
}
