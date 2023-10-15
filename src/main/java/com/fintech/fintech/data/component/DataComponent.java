package com.fintech.fintech.data.component;

import java.util.List;

public interface DataComponent<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void create(T object);

    void deleteById(ID id);
}
