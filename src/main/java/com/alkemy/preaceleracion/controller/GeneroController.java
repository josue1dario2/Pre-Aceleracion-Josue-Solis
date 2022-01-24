package com.alkemy.preaceleracion.controller;

import com.alkemy.preaceleracion.dto.GeneroDto;
import com.alkemy.preaceleracion.exception.SpringException;
import com.alkemy.preaceleracion.service.impl.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/genres")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl generoService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.status(OK).body(generoService.findAll());
        }catch (SpringException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try{
            return ResponseEntity.status(OK).body(generoService.findById(id));
        }catch (SpringException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GeneroDto dto) {
        try{
            return ResponseEntity.status(CREATED).body(generoService.save(dto));
        }catch (SpringException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody GeneroDto dto) {

        try{
            return ResponseEntity.status(OK).body(generoService.update(dto));
        }catch (SpringException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws SpringException {
        generoService.delete(id);
    }

}
