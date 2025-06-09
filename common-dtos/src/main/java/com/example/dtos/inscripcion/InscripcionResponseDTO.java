package com.example.dtos.inscripcion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionResponseDTO {
    private String usuarioId;
    private Long cursoId;
    private LocalDateTime fechaInscripcion;
    private String estado;
    private Float progreso;
}
