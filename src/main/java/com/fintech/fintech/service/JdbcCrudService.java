package com.fintech.fintech.service;

import java.util.List;

public interface JdbcCrudService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void create(T object);

    void deleteById(ID id);
}
