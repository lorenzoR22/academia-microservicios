package com.example.pagos_service.Controllers;

import com.example.dtos.pago.PagoRequestDTO;
import com.example.dtos.pago.PagoResponseDTO;
import com.example.exceptions.cursos.CursoNotFoundException;
import com.example.pagos_service.Exceptions.PagoNotFoundException;
import com.example.pagos_service.Services.PagoService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PagoResponseDTO getPago(@PathVariable Long id) throws PagoNotFoundException {
        return pagoService.getPago(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagoResponseDTO savePago(@Valid @RequestBody PagoRequestDTO dto,
                                    @AuthenticationPrincipal Jwt token){
        String usuarioId=token.getClaim("sub");
        return pagoService.savePago(usuarioId,dto);
    }

    @PostMapping("/comprar/{id_curso}")
    @ResponseStatus(HttpStatus.OK)
    public String getLinkPago(@AuthenticationPrincipal Jwt token,
                              @PathVariable Long id_curso) throws MPException, MPApiException, CursoNotFoundException {
        String email=token.getClaim("email");
        String id_user=token.getClaim("sub");
        return pagoService.getLinkCompra(id_user,email,id_curso);
    }

    @PostMapping("/chequear-pago")
    public void chequearPago(@RequestParam("data.id") String preapprovalId,
                                              @RequestParam("type") String topic) throws MPException, MPApiException {
        pagoService.chequearPago(preapprovalId, topic);
    }
}
