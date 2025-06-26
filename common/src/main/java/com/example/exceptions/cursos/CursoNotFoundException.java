package com.example.exceptions.cursos;

public class CursoNotFoundException extends RuntimeException{
    public CursoNotFoundException(Long id) {
        super("No se encontro el curso con el id: "+id);
    }
}
