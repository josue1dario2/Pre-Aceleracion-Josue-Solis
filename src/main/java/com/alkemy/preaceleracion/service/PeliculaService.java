package com.alkemy.preaceleracion.service;

import com.alkemy.preaceleracion.exception.SpringException;

import java.util.List;

public interface PeliculaService <T,ID>{

    public List<T> findAll();

    public List<T> findMovies();

    public T findById(ID id);

    public List<T> findByIdPelicula(Long peliculaId);

    public T save(T entity);

    public T update(ID id,T entity);

    public boolean delete(ID id);

    void addPersonaje(Long id,Long idIcono);

    void removePersonaje(Long id,Long idIcono);
}
