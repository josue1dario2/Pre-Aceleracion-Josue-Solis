package com.alkemy.preaceleracion.enums;

public enum Errors {
    ERROR1("La lista esta vacía"),
    ERROR2("El Id ingresado no se encuentra en la base de datos"),
    ERROR3("No se puedo realizar la modificación por que el Id ingresado no se encuentra en la base de datos"),
    ERROR4("Error al eliminar Id no válido");

    private final String error;

    Errors(String error) {
        this.error = error;
    }
}
