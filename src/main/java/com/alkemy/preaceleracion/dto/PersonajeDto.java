package com.alkemy.preaceleracion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonajeDto {

    private Long id;
    @NotBlank
    @Size(max = 255)
    private String imagen;
    @NotBlank
    @Size(max = 255)
    private String nombre;
    @NotNull
    private Integer edad;
    @NotNull
    private Double peso;
    @NotBlank
    @Size(max = 255)
    private String historia;
    private List<@Valid PeliculaDto> peliculas = new ArrayList<>();
}
