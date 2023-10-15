package com.fintech.fintech.data.repository.jdbc.impl;

import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.entity.Weather;
import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.data.enums.TemperatureScale;
import com.fintech.fintech.data.repository.jdbc.CrudRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CityRepositoryImpl extends CrudRepository<City, Long> {

    public CityRepositoryImpl(Class<City> classInfo, DataSource dataSource) {
        super(classInfo, dataSource);
    }

    @Override
    public City save(City object) {
        try (Connection connection = dataSource.getConnection()) {
            final String insertQuery = "INSERT INTO " + tableReference + " VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, object.getName());
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                return mapFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            log.error("cannot perform delete by id operation -> message: '{}'",
                      exception.getMessage());
        }
        return null;
    }

    @Override
    protected City mapFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(resultSet.getLong("id"), resultSet.getString("name"), new LinkedList<>());
    }

    private Weather mapWeatherFromResultSet(ResultSet resultSet) throws SQLException {
        return new Weather(resultSet.getLong("id"), resultSet.getDouble("temperature"),
                           TemperatureScale.valueOf(resultSet.getString("scale")),
                           ZonedDateTime.parse(resultSet.getString("datetime")), new WeatherType());
    }
}
