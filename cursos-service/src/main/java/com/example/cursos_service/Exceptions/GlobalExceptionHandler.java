package com.example.cursos_service.Exceptions;

import com.example.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<ErrorResponse>HandleNotFoundException(CursoNotFoundException e){
        ErrorResponse error=new ErrorResponse("CURSO_NOT_FOUND",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundLeccionException.class)
    public ResponseEntity<ErrorResponse>HandleNotFoundException(NotFoundLeccionException e){
        ErrorResponse error=new ErrorResponse("LECCION_NOT_FOUND",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WithoutInscripcionException.class)
    public ResponseEntity<ErrorResponse>HandleNotInscriptionException(WithoutInscripcionException e){
        ErrorResponse error=new ErrorResponse("WITHOUT_INSCRIPCION", e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorResponse error=new ErrorResponse("INTERNAL_SERVER_ERROR",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
