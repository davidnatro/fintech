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

    abstract public List<T> findAll();

    abstract public Optional<T> findById(ID id);

    abstract public T save(T object);

    abstract public void deleteById(ID id);

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
