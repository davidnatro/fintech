package com.fintech.fintech.data.component;

import java.util.List;

public interface JdbcDataComponent<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void create(T object);

    void update(ID id, T object);

    void deleteById(ID id);
}
