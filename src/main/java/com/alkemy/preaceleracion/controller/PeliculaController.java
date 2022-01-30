package com.alkemy.preaceleracion.controller;

import com.alkemy.preaceleracion.dto.PeliculaDto;
import com.alkemy.preaceleracion.dto.PersonajeDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.service.impl.PeliculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/movies")
public class PeliculaController {

    @Autowired
    private PeliculaServiceImpl peliculaService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<PeliculaDto> peliculas = peliculaService.findAll();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping(path = "movies")
    public ResponseEntity<?> getAllMovies(){
        List<PeliculaDto> peliculas = peliculaService.findMovies();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        PeliculaDto pelicula = peliculaService.findById(id);
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PeliculaDto t){
        PeliculaDto pelicula = peliculaService.save(t);
        return ResponseEntity.status(CREATED).body(pelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody PeliculaDto t){
        PeliculaDto pelicula = peliculaService.update(id,t);
        return ResponseEntity.ok(pelicula);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        peliculaService.delete(id);
    }
    @GetMapping(path = "/filtros")
    public ResponseEntity<?> getDetailsByFilters(
            @RequestParam(required = false)String nombre,
            @RequestParam(required = false) Long idGenero,
            @RequestParam(required = false,defaultValue = "ASC")String order){
        List<PersonajeDto> personajes = peliculaService.getByFilters(nombre, idGenero, order);
        return ResponseEntity.ok(personajes);
    }

    @PostMapping(path = "/{id}/personaje/{idPersonaje}")
    public ResponseEntity<Void> addPersonaje(@PathVariable Long id,@PathVariable Long idPersonaje){
        peliculaService.addPersonaje(id, idPersonaje);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping(path = "/{id}/personaje/{idPersonaje}")
    public ResponseEntity<Void> removePersonaje(@PathVariable Long id,@PathVariable Long idPersonaje){
        peliculaService.removePersonaje(id, idPersonaje);
        return ResponseEntity.ok().build();
    }

}
