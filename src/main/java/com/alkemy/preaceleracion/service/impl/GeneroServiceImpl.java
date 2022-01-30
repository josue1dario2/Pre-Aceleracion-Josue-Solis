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

    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private GeneroMapper generoConverter;

    @Override
    @Transactional
    public List<GeneroDto> findAll(){

            List<GeneroDto> generoDtos = new ArrayList<>();
            List<Genero> generos = generoRepository.findAll();

            if(generos.isEmpty()){
                throw new SpringException("La lista de géneros esta vacía");
            }
            for(Genero genero : generos){
                generoDtos.add(generoConverter.convertToDto(genero));
            }
            return generoDtos;

    }

    @Override
    @Transactional
    public GeneroDto findById(Long id){

            Optional<Genero> genero = generoRepository.findById(id);

            if(!genero.isPresent()){
                throw new SpringException("Id de género no válido");
            }
            return generoConverter.convertToDto(genero.get());

    }

    @Override
    @Transactional
    public GeneroDto save(GeneroDto dto){

            Genero genero = generoConverter.convertToEntity(dto);
            generoRepository.save(genero);
            return generoConverter.convertToDto(genero);

    }

    @Override
    @Transactional
    public GeneroDto update(GeneroDto dto){

            Genero genero = generoRepository.getById(dto.getId());
            if(genero == null){
                throw new SpringException("Id de género no válido");
            }
            genero = generoConverter.convertToEntity(dto);
            generoRepository.save(genero);

            return generoConverter.convertToDto(genero);

    }

    @Override
    @Transactional
    public void delete(Long id) {

            if(generoRepository.existsById(id)) {
                generoRepository.deleteById(id);
            }else {
                throw new SpringException("Id de género no válido");
            }

    }
}
