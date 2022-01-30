package com.alkemy.preaceleracion.controller;

import com.alkemy.preaceleracion.dto.GeneroDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.service.impl.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/genres")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<GeneroDto> generos = generoService.findAll();
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        GeneroDto genero = generoService.findById(id);
        return ResponseEntity.ok(genero);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GeneroDto dto) {
        GeneroDto genero = generoService.save(dto);
        return ResponseEntity.status(CREATED).body(genero);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody GeneroDto dto) {
        GeneroDto result = generoService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        generoService.delete(id);
    }

}
