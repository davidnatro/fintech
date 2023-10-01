package com.fintech.fintech.data.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    @NotNull
    private Double temperature;
    private LocalDateTime localDateTime = LocalDateTime.now();
}
