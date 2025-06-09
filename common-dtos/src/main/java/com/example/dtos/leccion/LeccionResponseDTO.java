package com.example.dtos.leccion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeccionResponseDTO {
    private Long cursoId;
    private String titulo;
    private String contenido;
    private Integer orden;
}
