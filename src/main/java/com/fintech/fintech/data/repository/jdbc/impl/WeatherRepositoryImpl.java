package com.fintech.fintech.data.repository.jdbc.impl;

import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.data.enums.TemperatureScale;
import com.fintech.fintech.data.repository.jdbc.CrudRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepositoryImpl extends CrudRepository<Weather, Long> {

    public WeatherRepositoryImpl(Class<Weather> classInfo, DataSource dataSource) {
        super(classInfo, dataSource);
    }

    @Override
    public List<Weather> findAll() {
        return null;
    }

    @Override
    public Optional<Weather> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Weather save(Weather object) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    protected Weather mapFromResultSet(ResultSet resultSet) throws SQLException {
        return new Weather(resultSet.getLong("weather_id"), resultSet.getDouble("temperature"),
                           TemperatureScale.valueOf(resultSet.getString("scale")),
                           new WeatherType(3L, null));
    }
}
