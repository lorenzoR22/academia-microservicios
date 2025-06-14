package com.example.pagos_service.Services.Impl;

import com.example.dtos.curso.CursoResponseDTO;
import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.pago.PagoRequestDTO;
import com.example.dtos.pago.PagoResponseDTO;
import com.example.pagos_service.Clients.CursoClient;
import com.example.pagos_service.Clients.InscripcionClient;
import com.example.pagos_service.Entities.Pago;
import com.example.pagos_service.Exceptions.PagoNotFoundException;
import com.example.pagos_service.Mappers.PagoMapper;
import com.example.pagos_service.Repositories.PagoRepository;
import com.example.pagos_service.Services.PagoService;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preapproval.PreApprovalAutoRecurringCreateRequest;
import com.mercadopago.client.preapproval.PreapprovalClient;
import com.mercadopago.client.preapproval.PreapprovalCreateRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preapproval.Preapproval;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final CursoClient cursoClient;
    private final InscripcionClient inscripcionClient;

    @Value("${mercadopago.accessToken}")
    private String mercadoPagoAccessToken;

    public PagoResponseDTO getPago(Long id) throws PagoNotFoundException {
        Pago pago=pagoRepository.findById(id)
                .orElseThrow(()->new PagoNotFoundException(id));
        return pagoMapper.toDTO(pago);
    }

    public PagoResponseDTO savePago(String usuarioId, PagoRequestDTO dto){
        Pago pago=pagoMapper.toEntity(dto);
        pago.setUsuarioId(usuarioId);
        Pago pagoSaved=pagoRepository.save(pago);
        return pagoMapper.toDTO(pagoSaved);
    }

    public String getLinkCompra(String id_user,String email,Long id_curso) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        CursoResponseDTO curso=cursoClient.getCurso(id_curso);

        PreapprovalClient client = new PreapprovalClient();

        PreapprovalCreateRequest request=PreapprovalCreateRequest.builder()
                .payerEmail(email)
                .backUrl("https://www.youtube.com/")
                .reason(curso.getTitulo())
                .externalReference(id_user+"|"+id_curso.toString())
                .autoRecurring(
                        PreApprovalAutoRecurringCreateRequest.builder()
                                .frequency(1)
                                .frequencyType("months")
                                .transactionAmount(curso.getPrecio())
                                .currencyId("ARS")
                                .startDate(OffsetDateTime.now().plusMinutes(1))
                                .endDate(OffsetDateTime.now().plusMonths(1))
                                .build()
                )
                .build();
        Preapproval preapproval = client.create(request);

        return preapproval.getInitPoint();
    }

    @Transactional
    public ResponseEntity<String> chequearPago(String preapprovalId,String topic ) throws MPApiException, MPException {
        if ("subscription_preapproval".equals(topic)) {
            PreapprovalClient client = new PreapprovalClient();
            Preapproval preapproval = client.get(preapprovalId);

            if ("authorized".equals(preapproval.getStatus())) {
                String externalRef = preapproval.getExternalReference(); // "usuarioId|cursoId"
                String[] partes = externalRef.split("\\|");
                String usuarioId = partes[0];
                Long cursoId = Long.valueOf(partes[1]);

                Pago nuevoPago = new Pago();
                nuevoPago.setUsuarioId(usuarioId);
                nuevoPago.setCursoId(cursoId);
                nuevoPago.setMonto(preapproval.getAutoRecurring().getTransactionAmount());
                nuevoPago.setMetodoDePago(preapproval.getPaymentMethodId());
                nuevoPago.setEstadoDePago(preapproval.getStatus());

                pagoRepository.save(nuevoPago);
                inscripcionClient.saveInscripcion(new InscripcionRequestDTO(cursoId), usuarioId);
            }
        }
        return ResponseEntity.ok("finalizado correctamente.");
    }

}
