package com.example.pagos_service.Services;

import com.example.dtos.pago.PagoRequestDTO;
import com.example.dtos.pago.PagoResponseDTO;
import com.example.exceptions.cursos.CursoNotFoundException;
import com.example.pagos_service.Exceptions.PagoNotFoundException;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;


public interface PagoService {
    PagoResponseDTO getPago(Long id) throws PagoNotFoundException;
    PagoResponseDTO savePago(String usuarioId, PagoRequestDTO dto);
    String getLinkCompra(String id_user,String email,Long id_curso) throws MPException, MPApiException, CursoNotFoundException;
    void chequearPago(String preapprovalId,String topic) throws MPException, MPApiException;
}
