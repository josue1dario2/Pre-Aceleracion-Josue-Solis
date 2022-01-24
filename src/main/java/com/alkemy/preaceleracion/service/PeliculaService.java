package com.alkemy.preaceleracion.service;

import com.alkemy.preaceleracion.exception.SpringException;

import java.util.List;

public interface PeliculaService <T,ID>{

    public List<T> findAll()throws SpringException;

    public List<T> findCities()throws SpringException;

    public T findById(ID id)throws SpringException;

    public List<T> findByIdPelicula(Long peliculaId)throws SpringException;

    public T save(T entity)throws SpringException;

    public T update(ID id,T entity)throws SpringException;

    public boolean delete(ID id)throws SpringException;

    void addPersonaje(Long id,Long idIcono)throws SpringException;

    void removePersonaje(Long id,Long idIcono)throws SpringException;
}
