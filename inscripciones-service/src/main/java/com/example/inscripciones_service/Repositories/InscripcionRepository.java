package com.example.inscripciones_service.Repositories;

import com.example.inscripciones_service.Entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    Boolean existsByUsuarioIdAndCursoId(String userId,Long cursoId);
}
