package com.fintech.fintech.data.repository.jdbc.impl;

import com.fintech.fintech.data.entity.WeatherType;
import com.fintech.fintech.data.repository.jdbc.CrudRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherTypeRepository extends CrudRepository<WeatherType, Long> {

    public WeatherTypeRepository(Class<WeatherType> classInfo, DataSource dataSource) {
        super(classInfo, dataSource);
    }

    @Override
    public List<WeatherType> findAll() {
        return null;
    }

    @Override
    public Optional<WeatherType> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public WeatherType save(WeatherType object) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    protected WeatherType mapFromResultSet(ResultSet resultSet) throws SQLException {
        return new WeatherType(resultSet.getLong("id"), resultSet.getString("type"));
    }
}
