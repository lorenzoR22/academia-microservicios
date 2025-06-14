package com.example.cursos_service.Exceptions;

public class CursoNotFoundException extends Exception{
    public CursoNotFoundException() {
    }

    public CursoNotFoundException(Long id) {
        super("No se encontro el curso con el id: "+id);
    }
}
