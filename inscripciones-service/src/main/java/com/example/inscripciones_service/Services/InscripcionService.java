package com.example.inscripciones_service.Services;


import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.inscripcion.InscripcionResponseDTO;
import com.example.dtos.inscripcion.InscripcionUpdateRequestDTO;
import com.example.inscripciones_service.Exceptions.NotFoundException;

public interface InscripcionService {
    InscripcionResponseDTO getInscripcion(Long id) throws NotFoundException;
    Boolean existByUserIdAndCursoId(String id_user,Long curso_id);
    InscripcionResponseDTO saveInscripcion(String userId, InscripcionRequestDTO dto);
    InscripcionResponseDTO updateInscripcion(Long id, InscripcionUpdateRequestDTO request) throws NotFoundException;
    InscripcionResponseDTO updateParcialInscripcion(Long id, InscripcionUpdateRequestDTO request) throws NotFoundException;
}
