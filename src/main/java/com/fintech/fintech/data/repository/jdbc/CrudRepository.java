package com.fintech.fintech.data.repository.jdbc;

import com.fintech.fintech.annotation.Table;
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

@Slf4j
public abstract class CrudRepository<T, ID> {

    protected final String tableReference;
    protected final DataSource dataSource;

    public CrudRepository(Class<T> classInfo, DataSource dataSource) {
        Table table = getTableAnnotation(classInfo);

        this.tableReference = constructTableReference(table);
        this.dataSource = dataSource;
    }

    public List<T> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            List<T> cities = new LinkedList<>();
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
        }
        return List.of();
    }

    public Optional<T> findById(ID id) {
        try (Connection connection = dataSource.getConnection()) {
            final String findByIdQuery = "SELECT * FROM " + tableReference + " c WHERE c.id=?";
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setString(1, id.toString());
            if (statement.execute()) {
                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                return Optional.of(mapFromResultSet(resultSet));
            }
        } catch (SQLException exception) {
            log.error("cannot perform find by id operation -> message: '{}'",
                      exception.getMessage());
        }
        return Optional.empty();
    }

    abstract public T save(T object);

    public void deleteById(ID id) {
        try (Connection connection = dataSource.getConnection()) {
            final String deleteByIdQuery = "DELETE FROM " + tableReference + " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(deleteByIdQuery);
            statement.setString(1, id.toString());
            statement.execute();
        } catch (SQLException exception) {
            log.error("cannot perform delete by id operation -> message: '{}'",
                      exception.getMessage());
        }
    }

    protected abstract T mapFromResultSet(ResultSet resultSet) throws SQLException;

    private Table getTableAnnotation(Class<T> classInfo) {
        if (classInfo.isAnnotationPresent(Table.class)) {
            return classInfo.getAnnotation(Table.class);
        }

        throw new UnsupportedOperationException(
                "cannot construct repository for " + classInfo.getName()
                        + "\nSpecify Table annotation!");
    }

    private String constructTableReference(Table table) {
        if (table.schema().isBlank()) {
            return table.name();
        }

        return table.schema() + "." + table.name();
    }
}
