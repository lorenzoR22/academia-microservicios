package com.example.cursos_service.Exceptions;

public class NotFoundLeccionException extends Exception{
    public NotFoundLeccionException() {
    }

    public NotFoundLeccionException(Long id_leccion) {
        super("No se encontro la leccion con el id: "+id_leccion);
    }
}
