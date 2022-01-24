package com.alkemy.preaceleracion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneroDto{

    private Long id;
    private String nombre;
    private String imagen;
}
