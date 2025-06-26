package com.example.cursos_service.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inscripciones")
@Getter
@Setter
@NoArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;

    private Long cursoId;

    public Inscripcion(String usuarioId, Long cursoId) {
        this.usuarioId = usuarioId;
        this.cursoId = cursoId;
    }
}
