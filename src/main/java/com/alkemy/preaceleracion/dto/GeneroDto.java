package com.alkemy.preaceleracion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneroDto{

    private Long id;
    @NotBlank
    @Size(max = 255)
    private String nombre;
    @NotBlank
    @Size(max = 255)
    private String imagen;
}
