package com.alkemy.preaceleracion.mapper;

import com.alkemy.preaceleracion.dto.GeneroDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.model.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    public GeneroDto convertToDto(Genero entity) throws SpringException {
        GeneroDto dto = new GeneroDto();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        return dto;
    }

    public Genero convertToEntity(GeneroDto dto) throws SpringException {
        Genero entity = new Genero();

        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());

        return entity;
    }
}
