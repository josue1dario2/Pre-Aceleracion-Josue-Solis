package com.alkemy.preaceleracion.service;

import com.alkemy.preaceleracion.exception.SpringException;

import java.util.List;

public interface GeneroService <T,ID>{

    public List<T> findAll();

    public T findById(ID id);

    public T save(T entity);

    public T update(T entity);

    public void delete(ID id);
}
