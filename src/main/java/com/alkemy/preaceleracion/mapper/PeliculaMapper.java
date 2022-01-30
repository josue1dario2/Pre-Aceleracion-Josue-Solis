package com.alkemy.preaceleracion.mapper;

import com.alkemy.preaceleracion.dto.PeliculaDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    @Autowired
    PersonajeMapper personajeMapper;

    public Pelicula convertToEntity(PeliculaDto dto) throws SpringException {
        Pelicula entity = new Pelicula();

        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaDeCreacion(stringToLocalDate(dto.getFechaDeCreacion()));
        entity.setImagen(dto.getImagen());
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());
        entity.setPersonajes(personajeMapper.convertDtoToEntityList(dto.getPersonajes()));

        return entity;
    }

    public PeliculaDto converToDto(Pelicula entity,boolean loadPersonaje)throws SpringException{
        PeliculaDto dto = new PeliculaDto();

        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setImagen(entity.getImagen());
        dto.setFechaDeCreacion(entity.getFechaDeCreacion().toString());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGeneroId(entity.getGeneroId());
        if(loadPersonaje){
            dto.setPersonajes(personajeMapper.convertEntityToDtoList(entity.getPersonajes(),false));
        }

        return dto;
    }

    public PeliculaDto convertToDtoCities(Pelicula paisEntity) {
        PeliculaDto dto = new PeliculaDto();
        dto.setImagen(paisEntity.getImagen());
        dto.setTitulo(paisEntity.getTitulo());
        dto.setCalificacion(paisEntity.getCalificacion());

        return dto;
    }

    public List<PeliculaDto> convertToDtoList(List<Pelicula> entities, boolean loadIcons) throws SpringException {
        List<PeliculaDto> dtos = new ArrayList<>();
        for(Pelicula pelicula : entities){
            dtos.add(converToDto(pelicula,loadIcons));
        }
        return  dtos;
    }
    public List<Pelicula> convertToEntityList(List<PeliculaDto> dtos) throws SpringException {
        List<Pelicula> peliculas = new ArrayList<>();
        for(PeliculaDto dto : dtos){
            peliculas.add(convertToEntity(dto));
        }
        return peliculas;
    }
    private LocalDate stringToLocalDate(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha,formato);
        return date;
    }
}
