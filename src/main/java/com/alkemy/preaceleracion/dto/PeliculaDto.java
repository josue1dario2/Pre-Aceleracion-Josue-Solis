package com.alkemy.preaceleracion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeliculaDto {

    private Long id;
    private String imagen;
    private String titulo;
    private String fechaDeCreacion;
    private Integer calificacion;
    private Long generoId;
    private List<PersonajeDto> personajes = new ArrayList<>();
}
