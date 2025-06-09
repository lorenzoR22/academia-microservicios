package com.example.dtos.leccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeccionRequestDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String contenido;
    @Positive
    @NotNull
    private Integer orden;

}
