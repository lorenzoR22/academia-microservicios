package com.example.cursos_service.Exceptions;

public class LeccionNotFoundException extends Exception{
    public LeccionNotFoundException() {
    }
    public LeccionNotFoundException(Long id_leccion) {
        super("No se encontro la leccion con el id: "+id_leccion);
    }
}
