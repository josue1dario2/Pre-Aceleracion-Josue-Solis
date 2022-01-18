package com.alkemy.preaceleracion.services;

import com.alkemy.preaceleracion.dtos.GeneroDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonajeServiceImpl implements PersonajeService<GeneroDto,Long>{
    @Override
    public List<GeneroDto> findAll() throws Exception {
        return null;
    }

    @Override
    public GeneroDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public GeneroDto save(GeneroDto entity) throws Exception {
        return null;
    }

    @Override
    public GeneroDto update(Long id, GeneroDto entity) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
