package com.example.dtos.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioResponseDTO {
    private String nombreCompleto;
    private String avatarUrl;
    private String bio;
    private LocalDate cumpleanos;
}
