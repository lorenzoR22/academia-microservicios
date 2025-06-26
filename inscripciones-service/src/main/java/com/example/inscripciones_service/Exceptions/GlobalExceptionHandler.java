package com.example.inscripciones_service.Exceptions;

import com.example.ErrorResponse;
import com.example.exceptions.inscripciones.AlreadyHaveInscripcionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InscripcionNotFoundException.class)
    public ResponseEntity<ErrorResponse>HandleNotFoundException(InscripcionNotFoundException e){
        ErrorResponse error=new ErrorResponse("INSCRIPCION_NOT_FOUND",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse error=new ErrorResponse("INTERNAL_SERVER_ERROR",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AlreadyHaveInscripcionException.class)
    public ResponseEntity<ErrorResponse>HandleAlreadyHaveInscripcionException(AlreadyHaveInscripcionException e){
        ErrorResponse error=new ErrorResponse("ALREADY_HAVE_INSCRIPCION",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
