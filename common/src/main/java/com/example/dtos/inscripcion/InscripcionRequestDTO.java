package com.example.dtos.inscripcion;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionRequestDTO {
    @NotNull
    private String userId;
    @NotNull
    private Long cursoId;
}
