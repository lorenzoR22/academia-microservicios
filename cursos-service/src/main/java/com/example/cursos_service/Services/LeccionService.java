package com.example.cursos_service.Services;


import com.example.cursos_service.Exceptions.LeccionNotFoundException;
import com.example.dtos.leccion.LeccionRequestDTO;
import com.example.dtos.leccion.LeccionResponseDTO;
import com.example.cursos_service.Exceptions.WithoutInscripcionException;
import com.example.exceptions.cursos.CursoNotFoundException;

public interface LeccionService {
    LeccionResponseDTO getLeccion(Long id,String id_user) throws CursoNotFoundException, WithoutInscripcionException, LeccionNotFoundException;
    LeccionResponseDTO saveLeccion(Long cursoId, LeccionRequestDTO dto) throws CursoNotFoundException;
    LeccionResponseDTO updateLeccion(Long leccionId,LeccionRequestDTO request) throws CursoNotFoundException, LeccionNotFoundException;
    LeccionResponseDTO updateParcialLeccion(Long leccionId, LeccionRequestDTO request) throws CursoNotFoundException, LeccionNotFoundException;
    void deleteLeccion(Long id);
}
