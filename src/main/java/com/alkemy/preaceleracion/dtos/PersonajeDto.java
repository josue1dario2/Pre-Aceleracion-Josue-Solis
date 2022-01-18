package com.alkemy.preaceleracion.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonajeDto {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    private List<PeliculaDto> peliculas = new ArrayList<>();
}
