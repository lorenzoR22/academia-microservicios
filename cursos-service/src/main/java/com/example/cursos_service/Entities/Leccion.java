package com.example.cursos_service.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")  // nombre de la FK en tabla Leccion
    private Curso curso; // Relacion con curso
    private String titulo;
    private String contenido;
    private Integer orden;//orden de la leccion dentro del curso

    private LocalDateTime creacion;
    private LocalDateTime ultimoUpdate;

    @PrePersist
    protected void onCreate() {
        this.creacion = LocalDateTime.now();
        this.ultimoUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.ultimoUpdate = LocalDateTime.now();
    }
}
