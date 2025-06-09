package com.example.cursos_service.Repositories;

import com.example.cursos_service.Entities.Leccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeccionRepository extends JpaRepository<Leccion,Long> {
}
