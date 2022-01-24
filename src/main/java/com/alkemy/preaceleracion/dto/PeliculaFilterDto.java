package com.alkemy.preaceleracion.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PeliculaFilterDto {

    private String nombre;
    private Long idGenero;
    private String order;

    public PeliculaFilterDto(String nombre, Long idGenero, String order) {
        this.nombre = nombre;
        this.idGenero = idGenero;
        this.order = order;
    }

    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }
    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
