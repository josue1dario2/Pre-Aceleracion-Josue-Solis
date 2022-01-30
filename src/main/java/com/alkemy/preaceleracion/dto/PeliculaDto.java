package com.alkemy.preaceleracion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeliculaDto {

    private Long id;
    @NotBlank
    @Size(max = 255)
    private String imagen;
    @NotBlank
    @Size(max = 255)
    private String titulo;
    @NotBlank
    private String fechaDeCreacion;
    @NotNull
    private Integer calificacion;
    @NotNull
    private Long generoId;
    private List<@Valid PersonajeDto> personajes = new ArrayList<>();
}
