package com.alkemy.preaceleracion.service.impl;

import com.alkemy.preaceleracion.dto.PeliculaDto;
import com.alkemy.preaceleracion.dto.PeliculaFilterDto;
import com.alkemy.preaceleracion.dto.PersonajeDto;
import com.alkemy.preaceleracion.enums.Errors;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.mapper.PeliculaMapper;
import com.alkemy.preaceleracion.mapper.PersonajeMapper;
import com.alkemy.preaceleracion.model.Pelicula;
import com.alkemy.preaceleracion.model.Personaje;
import com.alkemy.preaceleracion.repository.GeneroRepository;
import com.alkemy.preaceleracion.repository.PeliculaRepository;
import com.alkemy.preaceleracion.repository.PersonajeRepository;
import com.alkemy.preaceleracion.service.PeliculaService;
import com.alkemy.preaceleracion.service.specification.PeliculaEspecificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService<PeliculaDto,Long> {

    @Autowired
    private PersonajeServiceImpl personajeService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PeliculaEspecificacion peliculaEspecificacion;

    @Override
    @Transactional
    public List<PeliculaDto> findAll(){

            List<Pelicula> paises  = peliculaRepository.findAll();
            if(paises.isEmpty()){
                throw new SpringException(Errors.ERROR1);
            }
            List<PeliculaDto> paisesDto = peliculaMapper.convertToDtoList(paises,true);
            return paisesDto;

    }

    @Transactional
    public List<PersonajeDto> getByFilters(String nombre, Long idGenero,String order) {
        PeliculaFilterDto filterDto = new PeliculaFilterDto(nombre, idGenero, order);
        List<Personaje> entities = personajeRepository.findAll(peliculaEspecificacion.getByFilters(filterDto));
        List<PersonajeDto> dtos = personajeMapper.convertEntityToDtoList(entities,true);
        return dtos;
    }

    @Override
    public List<PeliculaDto> findMovies(){

            List<Pelicula> pelicula  = peliculaRepository.findAll();
            if(pelicula.isEmpty()){
                throw new SpringException(Errors.ERROR1);
            }
            List<PeliculaDto> peliculaDto = new ArrayList<>();

            for(Pelicula movie : pelicula){
                peliculaDto.add(peliculaMapper.convertToDtoCities(movie));
            }

            return peliculaDto;

    }

    @Override
    @Transactional
    public PeliculaDto findById(Long id){

            Optional<Pelicula> entity = peliculaRepository.findById(id);

            if(!entity.isPresent()){
                throw new SpringException(Errors.ERROR2);
            }
            return peliculaMapper.converToDto(entity.get(),false);

    }

    @Override
    public List<PeliculaDto> findByIdPelicula(Long peliculaId){

            List<Pelicula> pelicula  = peliculaRepository.findByIdPelicula(peliculaId);
            List<PeliculaDto> peliculaDto = new ArrayList<>();

            if(pelicula.isEmpty()){
                throw new SpringException(Errors.ERROR2);
            }
            for(Pelicula movie : pelicula){
                peliculaDto.add(peliculaMapper.convertToDtoCities(movie));
            }

            return peliculaDto;

    }

    @Override
    @Transactional
    public PeliculaDto save(PeliculaDto peliculaDto){

            Pelicula entity = peliculaMapper.convertToEntity(peliculaDto);
            peliculaRepository.save(entity);

            return peliculaMapper.converToDto(entity,true);

    }

    @Override
    @Transactional
    public PeliculaDto update(Long id, PeliculaDto peliculaDto){

            Optional<Pelicula> pelicula = peliculaRepository.findById(id);

            if(!pelicula.isPresent()){
                throw new SpringException(Errors.ERROR3);
            }
            Pelicula entity = peliculaMapper.convertToEntity(peliculaDto);
            peliculaRepository.save(entity);
            return peliculaMapper.converToDto(pelicula.get(),false);

    }

    @Override
    @Transactional
    public boolean delete(Long id){

            if(peliculaRepository.existsById(id)){
                peliculaRepository.deleteById(id);
                return true;
            }else{
                throw new SpringException(Errors.ERROR4);
            }
    }

    @Override
    public void addPersonaje(Long id, Long idPersonaje){

            Optional<Pelicula> pelicula = peliculaRepository.findById(id);
            if(!pelicula.isPresent()){
                throw new SpringException(Errors.ERROR2);
            }
            Pelicula peliculaEntity = pelicula.get();
            peliculaEntity.getPersonajes().size();
            PersonajeDto personajeDto = personajeService.findById(idPersonaje);
            peliculaEntity.addPersonaje(personajeMapper.convertToEntity(personajeDto));
            peliculaRepository.save(peliculaEntity);

    }

    @Override
    public void removePersonaje(Long id, Long idPersonaje){

            Optional<Pelicula> pais = peliculaRepository.findById(id);
            if(!pais.isPresent()){
                throw new SpringException(Errors.ERROR2);
            }
            Pelicula peliculaEntity = pais.get();
            peliculaEntity.getPersonajes().size();
            PersonajeDto iconoDto = personajeService.findById(idPersonaje);
            peliculaEntity.removePersonaje(personajeMapper.convertToEntity(iconoDto));
            peliculaRepository.save(peliculaEntity);

    }
}
