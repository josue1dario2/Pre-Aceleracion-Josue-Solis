package com.alkemy.preaceleracion.services;

import java.util.List;

public interface GeneroService <T,ID>{

    public List<T> findAll()throws Exception;

    public T findById(ID id)throws Exception;

    public T save(T entity)throws Exception;

    public T update(ID id,T entity)throws Exception;

    public void delete(ID id)throws Exception;
}
