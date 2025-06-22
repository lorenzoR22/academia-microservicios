package com.example.pagos_service.Exceptions;

import com.example.ErrorResponse;
import com.mercadopago.exceptions.MPApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PagoNotFoundException.class)
    public ResponseEntity<ErrorResponse>HandleNotFoundException(PagoNotFoundException e){
        ErrorResponse error=new ErrorResponse("NOT_FOUND",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MPApiException.class)
    public ResponseEntity<ErrorResponse>handleMPApiException(MPApiException e){
        ErrorResponse error=new ErrorResponse("MP_ERROR",e.getApiResponse().getContent());
        return new ResponseEntity<>(error,HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        ErrorResponse error=new ErrorResponse("INTERNAL_SERVER_ERROR",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ServicioNoDisponibleException.class)
    public ResponseEntity<ErrorResponse>HandleServicioNoDisponibleException(ServicioNoDisponibleException ex) {
        ErrorResponse error=new ErrorResponse("SERVICE_UNAVAILABLE",ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
