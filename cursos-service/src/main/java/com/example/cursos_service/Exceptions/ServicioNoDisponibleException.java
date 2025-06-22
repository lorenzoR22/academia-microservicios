package com.example.cursos_service.Exceptions;

public class ServicioNoDisponibleException extends RuntimeException{
    public ServicioNoDisponibleException(String servicio, Throwable causa) {
        super("El servicio '" + servicio + "' no está disponible en este momento.", causa);
    }
}
