package com.example.cursos_service.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String categoria;
    private String profesor;
    private BigDecimal precio;

    private LocalDateTime creacion;
    private LocalDateTime ultimoUpdate;

    private String estado; // Activo, Inactivo, etc.

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leccion> lecciones = new ArrayList<>();

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
