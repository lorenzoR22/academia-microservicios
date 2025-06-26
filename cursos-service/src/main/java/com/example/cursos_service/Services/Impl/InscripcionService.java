package com.example.cursos_service.Services.Impl;

import com.example.cursos_service.Entities.Inscripcion;
import com.example.cursos_service.Repositories.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;

    public void saveInscripcion(String userId,Long cursoId){
        inscripcionRepository.save(new Inscripcion(userId,cursoId));
    }
    public Boolean existsInscripcion(String userId,Long cursoId){
        return inscripcionRepository.existsByUsuarioIdAndCursoId(userId,cursoId);
    }

}
