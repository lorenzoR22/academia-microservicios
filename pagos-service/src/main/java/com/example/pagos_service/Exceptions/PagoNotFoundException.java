package com.example.pagos_service.Exceptions;

public class PagoNotFoundException extends Exception{
    public PagoNotFoundException() {
    }

    public PagoNotFoundException(Long id) {
        super("No se encontro el pago con el id:"+id);
    }
}
