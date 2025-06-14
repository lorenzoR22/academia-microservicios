package com.example.cursos_service.Services;


import com.example.cursos_service.Exceptions.NotFoundLeccionException;
import com.example.dtos.leccion.LeccionRequestDTO;
import com.example.dtos.leccion.LeccionResponseDTO;
import com.example.cursos_service.Exceptions.CursoNotFoundException;
import com.example.cursos_service.Exceptions.WithoutInscripcionException;

public interface LeccionService {
    LeccionResponseDTO getLeccion(Long id,String id_user) throws CursoNotFoundException, WithoutInscripcionException;
    LeccionResponseDTO saveLeccion(Long cursoId, LeccionRequestDTO dto) throws CursoNotFoundException;
    LeccionResponseDTO updateLeccion(Long leccionId,LeccionRequestDTO request) throws CursoNotFoundException, NotFoundLeccionException;
    LeccionResponseDTO updateParcialLeccion(Long leccionId, LeccionRequestDTO request) throws CursoNotFoundException, NotFoundLeccionException;
    void deleteLeccion(Long id);
}
