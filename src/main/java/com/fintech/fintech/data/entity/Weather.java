package com.fintech.fintech.data.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    private Long id;
    private String regionName;
    private Integer temperature;
    private LocalDateTime dateTime;
}
