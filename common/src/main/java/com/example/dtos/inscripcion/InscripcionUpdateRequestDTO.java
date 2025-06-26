package com.example.dtos.inscripcion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionUpdateRequestDTO {
    @NotBlank
    private String estado;
    @NotNull
    private Float progreso;
}
