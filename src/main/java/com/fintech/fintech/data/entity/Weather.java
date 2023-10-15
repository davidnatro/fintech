package com.fintech.fintech.data.entity;

import com.fintech.fintech.annotation.Table;
import com.fintech.fintech.data.enums.TemperatureScale;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather", schema = "weather")
@jakarta.persistence.Table(name = "weather", schema = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperature;

    private TemperatureScale scale;

    @ManyToOne
    @JoinColumn(name = "weather_type_id")
    private WeatherType type;
}
