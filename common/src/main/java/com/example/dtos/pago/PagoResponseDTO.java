package com.example.dtos.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {
    private String usuarioId;
    private Long cursoId;
    private BigDecimal monto;
    private String estadoDePago;
    private String metodoDePago;
    private LocalDateTime fechaPago;
}
