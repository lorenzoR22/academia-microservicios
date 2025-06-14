package com.example.inscripciones_service.Exceptions;

public class InscripcionNotFoundException extends Exception{
    public InscripcionNotFoundException() {
    }

    public InscripcionNotFoundException(Long id) {
        super("No se encontro la inscripcion con id:"+id);
    }
}
