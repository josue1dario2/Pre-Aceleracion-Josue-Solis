package com.alkemy.preaceleracion.mapper;

import com.alkemy.preaceleracion.dto.PersonajeDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.model.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public Personaje convertToEntity(PersonajeDto dto)throws SpringException {
        Personaje entity = new Personaje();

        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

        return entity;
    }

    public PersonajeDto convertToDto(Personaje entity,boolean loadPelicula) throws SpringException{
        PersonajeDto dto = new PersonajeDto();

        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if(loadPelicula){
            dto.setPeliculas(peliculaMapper.convertToDtoList(entity.getPeliculas(),false));
        }

        return dto;
    }

    public PersonajeDto listaPersonaje(Personaje entity) {
        PersonajeDto dto = new PersonajeDto();
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());

        return dto;
    }
    public List<Personaje> convertDtoToEntityList(List<PersonajeDto> dtos) throws SpringException {
        List<Personaje> personajes = new ArrayList<>();

        for(PersonajeDto dto : dtos){
            personajes.add(convertToEntity(dto));
        }
        return personajes;
    }
    public List<PersonajeDto> convertEntityToDtoList(List<Personaje> entities, boolean loadPeliculas) throws SpringException {
        List<PersonajeDto> dtos = new ArrayList<>();
        for(Personaje entity : entities){
            dtos.add(convertToDto(entity,loadPeliculas));
        }
        return dtos;
    }

    public void iconoEntityRefreshValues(Personaje entity,PersonajeDto dto){
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

    }
}
