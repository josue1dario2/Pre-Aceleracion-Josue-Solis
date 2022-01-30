package com.alkemy.preaceleracion.service;

import com.alkemy.preaceleracion.exception.SpringException;

import java.util.List;

public interface PersonajeService <T,ID>{

    public List<T> findAll();

    public List<T> findAllPersonajes();

    public T findById(ID id);

    public T save(T entity);

    public T update(ID id,T entity);

    public void delete(ID id);
}
