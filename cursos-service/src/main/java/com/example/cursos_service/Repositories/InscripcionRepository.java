package com.example.cursos_service.Repositories;

import com.example.cursos_service.Entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {
    Boolean existsByUsuarioIdAndCursoId(String userId,Long cursoId);
}
