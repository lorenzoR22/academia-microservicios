package com.example.cursos_service.Exceptions;

public class WithoutInscripcionException extends Exception{
    public WithoutInscripcionException(String curso_titulo, String id_user) {
        super("No se encontro inscripcion al curso "+curso_titulo+" con el id: "+id_user);
    }

    public WithoutInscripcionException() {
    }
}
