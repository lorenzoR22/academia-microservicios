package com.example.inscripciones_service.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;  // El 'sub' del token
    private Long cursoId;  // Relación con curso
    private LocalDateTime fechaInscripcion;
    private String estado;  // Activo, Completado, Abandonado, etc.
    private Float progreso; // 0 a 100%

    @PrePersist
    protected void onCreate() {
        this.fechaInscripcion = LocalDateTime.now();
        this.estado="Activo";
        this.progreso=0.0f;
    }
}
