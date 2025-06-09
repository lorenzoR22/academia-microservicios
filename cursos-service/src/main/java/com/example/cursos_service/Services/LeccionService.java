package com.example.cursos_service.Services;


import com.example.dtos.leccion.LeccionRequestDTO;
import com.example.dtos.leccion.LeccionResponseDTO;
import com.example.cursos_service.Exceptions.NotFoundException;
import com.example.cursos_service.Exceptions.NotInscriptionException;

public interface LeccionService {
    LeccionResponseDTO getLeccion(Long id,String id_user) throws NotFoundException, NotInscriptionException;
    LeccionResponseDTO saveLeccion(Long cursoId, LeccionRequestDTO dto) throws NotFoundException;
    LeccionResponseDTO updateLeccion(Long leccionId,LeccionRequestDTO request) throws NotFoundException;
    LeccionResponseDTO updateParcialLeccion(Long leccionId, LeccionRequestDTO request) throws NotFoundException;
    void deleteLeccion(Long id);
}
