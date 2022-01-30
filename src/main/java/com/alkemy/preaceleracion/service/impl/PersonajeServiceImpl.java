package com.alkemy.preaceleracion.service.impl;

import com.alkemy.preaceleracion.dto.GeneroDto;
import com.alkemy.preaceleracion.dto.PersonajeDto;
import com.alkemy.preaceleracion.dto.PersonajeFilterDto;
import com.alkemy.preaceleracion.enums.Errors;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.mapper.PersonajeMapper;
import com.alkemy.preaceleracion.model.Personaje;
import com.alkemy.preaceleracion.repository.PeliculaRepository;
import com.alkemy.preaceleracion.repository.PersonajeRepository;
import com.alkemy.preaceleracion.service.GeneroService;
import com.alkemy.preaceleracion.service.PersonajeService;
import com.alkemy.preaceleracion.service.specification.PersonajeEspecificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService<PersonajeDto,Long> {

    @Autowired
    private PeliculaServiceImpl peliculaService;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeEspecificacion personajeEspecificacion;

    @Override
    @Transactional
    public List<PersonajeDto> findAll(){

            List<Personaje> personajes  = personajeRepository.findAll();
            if(personajes.isEmpty()){
                throw new SpringException(Errors.ERROR1);
            }
            List<PersonajeDto> iconosDtos = personajeMapper.convertEntityToDtoList(personajes,false);
            return iconosDtos;

    }
    @Transactional
    public List<PersonajeDto> getByFilters(String nombre, String edad, Set<Long> paises){
        PersonajeFilterDto filtersDTO = new PersonajeFilterDto(nombre, edad, paises);
        List<Personaje> entities = personajeRepository.findAll(personajeEspecificacion.getByFilters(filtersDTO));
        List<PersonajeDto> dtos = personajeMapper.convertEntityToDtoList(entities,true);
        return dtos;
    }

    @Override
    public List<PersonajeDto> findAllPersonajes() {

            List<Personaje> personajes  = personajeRepository.findAll();
            List<PersonajeDto> personajeDtos = new ArrayList<>();

            if(personajes.isEmpty()){
                throw new SpringException(Errors.ERROR1);
            }
            for(Personaje personaje : personajes){
                personajeDtos.add(personajeMapper.listaPersonaje(personaje));
            }
            return personajeDtos;

    }

    @Override
    @Transactional
    public PersonajeDto findById(Long id){

            Optional<Personaje> personaje = personajeRepository.findById(id);
            if(!personaje.isPresent()){
                throw new SpringException(Errors.ERROR2);
            }
            return personajeMapper.convertToDto(personaje.get(),false);

    }

    @Override
    public PersonajeDto save(PersonajeDto dto){

            Personaje personaje = personajeMapper.convertToEntity(dto);
            personajeRepository.save(personaje);

            return personajeMapper.convertToDto(personaje,false);

    }

    @Override
    public PersonajeDto update(Long id, PersonajeDto dto) {

            Optional<Personaje> personaje = personajeRepository.findById(id);

            if(!personaje.isPresent()){
                throw new SpringException(Errors.ERROR3);
            }
            Personaje entity = personajeMapper.convertToEntity(dto);
            personajeRepository.save(entity);

            return personajeMapper.convertToDto(personaje.get(),false);

    }

    @Override
    @Transactional
    public void delete(Long id)  {

            if(personajeRepository.existsById(id)){
                personajeRepository.deleteById(id);
            }else{
                throw new SpringException(Errors.ERROR4);
            }
    }
}
