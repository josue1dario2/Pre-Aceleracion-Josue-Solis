package com.alkemy.preaceleracion.service.impl;

import com.alkemy.preaceleracion.dto.GeneroDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.mapper.GeneroMapper;
import com.alkemy.preaceleracion.model.Genero;
import com.alkemy.preaceleracion.repository.GeneroRepository;
import com.alkemy.preaceleracion.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService<GeneroDto,Long> {

    private final String ERROR_1 = "No hay géneros en la base de datos";
    private final String ERROR_2 = "El género no esta en la base de datos";
    private final String ERROR_3 = "Error en el servidor";

    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private GeneroMapper generoConverter;

    @Override
    @Transactional
    public List<GeneroDto> findAll() throws SpringException {
        try{
            List<GeneroDto> generoDtos = new ArrayList<>();
            List<Genero> generos = generoRepository.findAll();

            if(generos.isEmpty()){
                throw new SpringException(ERROR_1);
            }
            for(Genero genero : generos){
                generoDtos.add(generoConverter.convertToDto(genero));
            }
            return generoDtos;
        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public GeneroDto findById(Long id) throws SpringException {
        try{
            Optional<Genero> genero = generoRepository.findById(id);

            if(!genero.isPresent()){
                throw new SpringException(ERROR_2);
            }
            return generoConverter.convertToDto(genero.get());
        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public GeneroDto save(GeneroDto dto) throws SpringException {
        try{
            Genero genero = generoConverter.convertToEntity(dto);
            generoRepository.save(genero);
            return generoConverter.convertToDto(genero);
        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public GeneroDto update(GeneroDto dto) throws SpringException {
        try{
            Genero genero = generoRepository.getById(dto.getId());
            if(genero == null){
                throw new SpringException(ERROR_2);
            }
            genero = generoConverter.convertToEntity(dto);
            generoRepository.save(genero);

            return generoConverter.convertToDto(genero);
        }catch (Exception e){
            throw new SpringException(ERROR_1);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws SpringException {
        try{
            if(generoRepository.existsById(id)) {
                generoRepository.deleteById(id);
            }else {
                throw new SpringException(ERROR_2);
            }
        }catch (Exception e){
            throw new SpringException(e.getMessage());
        }
    }
}
