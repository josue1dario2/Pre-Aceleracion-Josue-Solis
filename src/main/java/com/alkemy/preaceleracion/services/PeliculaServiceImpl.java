package com.alkemy.preaceleracion.services;

import com.alkemy.preaceleracion.dtos.PeliculaDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PeliculaServiceImpl implements PeliculaService <PeliculaDto,Long>{
    @Override
    public List<PeliculaDto> findAll() throws Exception {
        return null;
    }

    @Override
    public PeliculaDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public PeliculaDto save(PeliculaDto entity) throws Exception {
        return null;
    }

    @Override
    public PeliculaDto update(Long id, PeliculaDto entity) throws Exception {
        return null;
    }

    @Override
    public void delete(Long id) throws Exception {

    }
}
