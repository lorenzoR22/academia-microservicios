package com.example.pagos_service.Services;

import com.example.dtos.pago.PagoRequestDTO;
import com.example.dtos.pago.PagoResponseDTO;
import com.example.pagos_service.Exceptions.NotFoundException;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.http.ResponseEntity;


public interface PagoService {
    PagoResponseDTO getPago(Long id) throws NotFoundException;
    PagoResponseDTO savePago(String usuarioId, PagoRequestDTO dto);
    String getLinkCompra(String id_user,String email,Long id_curso) throws MPException, MPApiException;
    ResponseEntity<String> chequearPago(String preapprovalId,String topic) throws MPException, MPApiException;
}
