package com.fintech.fintech.data.repository.jdbc.impl;

import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.data.repository.jdbc.CrudRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class WeatherTypeRepositoryImpl extends CrudRepository<WeatherType, Long> {

    public WeatherTypeRepositoryImpl(Class<WeatherType> classInfo, DataSource dataSource) {
        super(classInfo, dataSource);
    }

    @Override
    public WeatherType save(WeatherType object) {
        try (Connection connection = dataSource.getConnection()) {
            final String insertQuery = "INSERT INTO " + tableReference + " VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, object.getType());
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
    protected WeatherType mapFromResultSet(ResultSet resultSet) throws SQLException {
        return new WeatherType(resultSet.getLong("id"), resultSet.getString("type"));
    }
}
