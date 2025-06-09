package com.example.dtos.pago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDTO {
    @NotNull
    private Long cursoId;
    @NotNull
    private BigDecimal monto;
    @NotBlank
    private String estadoDePago;
    @NotBlank
    private String metodoDePago;
}
