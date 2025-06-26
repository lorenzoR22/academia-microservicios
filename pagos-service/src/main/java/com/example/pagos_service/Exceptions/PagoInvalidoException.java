package com.example.pagos_service.Exceptions;

public class PagoInvalidoException extends RuntimeException{
    public PagoInvalidoException(String message,Throwable cause) {
        super(message,cause);
    }
}
