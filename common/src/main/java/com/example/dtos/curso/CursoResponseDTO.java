package com.example.dtos.curso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoResponseDTO {
    private String titulo;
    private String descripcion;
    private String categoria;
    private String profesor;
    private BigDecimal precio;
    private String estado;
}
