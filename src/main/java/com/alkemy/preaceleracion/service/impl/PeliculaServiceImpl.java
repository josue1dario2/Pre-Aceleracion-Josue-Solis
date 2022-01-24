package com.alkemy.preaceleracion.service.impl;

import com.alkemy.preaceleracion.dto.PeliculaDto;
import com.alkemy.preaceleracion.dto.PeliculaFilterDto;
import com.alkemy.preaceleracion.dto.PersonajeDto;
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

    private static String ERROR_1 = "No hay peliculas en la base de datos";
    private static String ERROR_2 = "La pelicula no esta en la base de datos";
    private static String ERROR_3 = "La denominación ingresada no existe en la base de datos";
    private static String ERROR_4 = "El id ingresado no tiene personajes vinculados";
    private static String ERROR_5 = "El orden ingresado no es válido";

    @Override
    @Transactional
    public List<PeliculaDto> findAll() throws SpringException {
        try{
            List<Pelicula> paises  = peliculaRepository.findAll();
            if(paises.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<PeliculaDto> paisesDto = peliculaMapper.convertToDtoList(paises,true);
            return paisesDto;

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Transactional
    public List<PersonajeDto> getByFilters(String nombre, Long idGenero,String order) throws SpringException {
        PeliculaFilterDto filterDto = new PeliculaFilterDto(nombre, idGenero, order);
        List<Personaje> entities = personajeRepository.findAll(peliculaEspecificacion.getByFilters(filterDto));
        List<PersonajeDto> dtos = personajeMapper.convertEntityToDtoList(entities,true);
        return dtos;
    }

    @Override
    public List<PeliculaDto> findCities() throws SpringException {
        try{
            List<Pelicula> paises  = peliculaRepository.findAll();
            if(paises.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            List<PeliculaDto> paisesDto = new ArrayList<>();

            for(Pelicula pais : paises){
                paisesDto.add(peliculaMapper.convertToDtoCities(pais));
            }

            return paisesDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PeliculaDto findById(Long id) throws SpringException {
        try{
            Optional<Pelicula> entity = peliculaRepository.findById(id);

            if(!entity.isPresent()){
                throw new SpringException(ERROR_2);
            }
            return peliculaMapper.converToDto(entity.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public List<PeliculaDto> findByIdPelicula(Long peliculaId) throws SpringException {
        try{
            List<Pelicula> paises  = peliculaRepository.findByIdPelicula(peliculaId);
            List<PeliculaDto> paisesDto = new ArrayList<>();

            if(paises.isEmpty()){
                throw new SpringException(ERROR_4);
            }
            for(Pelicula pais : paises){
                paisesDto.add(peliculaMapper.convertToDtoCities(pais));
            }

            return paisesDto;
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PeliculaDto save(PeliculaDto peliculaDto) throws SpringException {
        try{
            Pelicula entity = peliculaMapper.convertToEntity(peliculaDto);
            peliculaRepository.save(entity);

            return peliculaMapper.converToDto(entity,false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public PeliculaDto update(Long id, PeliculaDto peliculaDto) throws SpringException {
        try{
            Optional<Pelicula> pais = peliculaRepository.findById(id);

            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            Pelicula entity = peliculaMapper.convertToEntity(peliculaDto);
            peliculaRepository.save(entity);
            return peliculaMapper.converToDto(pais.get(),false);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws SpringException {
        try{
            if(peliculaRepository.existsById(id)){
                peliculaRepository.deleteById(id);
                return true;
            }else{
                throw new SpringException(ERROR_2);
            }
        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public void addPersonaje(Long id, Long idPersonaje) throws SpringException {
        try{
            Optional<Pelicula> pais = peliculaRepository.findById(id);
            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            Pelicula peliculaEntity = pais.get();
            peliculaEntity.getPersonajes().size();
            PersonajeDto personajeDto = personajeService.findById(idPersonaje);
            peliculaEntity.addPersonaje(personajeMapper.convertToEntity(personajeDto));
            peliculaRepository.save(peliculaEntity);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    public void removePersonaje(Long id, Long idPersonaje) throws SpringException {
        try{
            Optional<Pelicula> pais = peliculaRepository.findById(id);
            if(!pais.isPresent()){
                throw new SpringException(ERROR_2);
            }
            Pelicula peliculaEntity = pais.get();
            peliculaEntity.getPersonajes().size();
            PersonajeDto iconoDto = personajeService.findById(idPersonaje);
            peliculaEntity.removePersonaje(personajeMapper.convertToEntity(iconoDto));
            peliculaRepository.save(peliculaEntity);

        }catch (SpringException e){
            throw new SpringException(e.getMessage());
        }
    }
}
