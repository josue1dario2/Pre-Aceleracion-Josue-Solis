package com.alkemy.preaceleracion.controller;

import com.alkemy.preaceleracion.dto.PersonajeDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.service.impl.PersonajeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/characters")
public class PersonajeController {

    @Autowired
    private PersonajeServiceImpl personajeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<PersonajeDto> personajes = personajeService.findAll();
        return ResponseEntity.ok(personajes);
    }
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllPersonajes(){
        List<PersonajeDto> personajes = personajeService.findAllPersonajes();
        return ResponseEntity.ok(personajes);
    }
    @GetMapping(path = "/filtros")
    public ResponseEntity<?> getDetailsByFilters(
            @RequestParam(required = false)String nombre,
            @RequestParam(required = false)String edad,
            @RequestParam(required = false) Set<Long> paises){
        List<PersonajeDto> personajes = personajeService.getByFilters(nombre, edad, paises);
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        PersonajeDto personaje = personajeService.findById(id);
        return ResponseEntity.ok(personaje);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PersonajeDto t){
        PersonajeDto personaje = personajeService.save(t);
        return ResponseEntity.status(CREATED).body(personaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody PersonajeDto t){
        PersonajeDto personaje = personajeService.update(id,t);
        return ResponseEntity.ok(personaje);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        personajeService.delete(id);
    }
}
