package com.fintech.fintech.data.entity;

import com.fintech.fintech.annotation.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities", schema = "weather")
@jakarta.persistence.Table(name = "cities", schema = "weather")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "city_weather",
            joinColumns = @JoinColumn(referencedColumnName = "city_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "weather_id"))
    private List<Weather> weathers = new LinkedList<>();
}
