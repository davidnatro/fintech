package com.fintech.fintech.data.repository.jdbc.impl;

import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.repository.jdbc.CrudRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CityRepository extends CrudRepository<City, Long> {

    public CityRepository(Class<City> classInfo, DataSource dataSource) {
        super(classInfo, dataSource);
    }

    @Override
    public List<City> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            List<City> cities = new LinkedList<>();
            final String findAllQuery = "SELECT * FROM " + tableReference;
            Statement statement = connection.createStatement();
            if (statement.execute(findAllQuery)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    cities.add(mapFromResultSet(resultSet));
                }
            }
            return cities;
        } catch (SQLException exception) {
            log.error("cannot perform find all operation -> message: '{}'", exception.getMessage());
            return List.of();
        }
    }

    @Override
    public Optional<City> findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            final String findByIdQuery = "SELECT * FROM " + tableReference + " c WHERE c.id=?";
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setString(1, id.toString());
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                return Optional.of(mapFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException exception) {
            log.error("cannot perform find by id operation -> message: '{}'",
                      exception.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public City save(City object) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    protected City mapFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(resultSet.getLong("id"), resultSet.getString("name"), null);
    }
}
