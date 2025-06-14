package com.example.inscripciones_service.Services;


import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.inscripcion.InscripcionResponseDTO;
import com.example.dtos.inscripcion.InscripcionUpdateRequestDTO;
import com.example.inscripciones_service.Exceptions.InscripcionNotFoundException;

public interface InscripcionService {
    InscripcionResponseDTO getInscripcion(Long id) throws InscripcionNotFoundException;
    Boolean existByUserIdAndCursoId(String id_user,Long curso_id);
    InscripcionResponseDTO saveInscripcion(String userId, InscripcionRequestDTO dto);
    InscripcionResponseDTO updateInscripcion(Long id, InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException;
    InscripcionResponseDTO updateParcialInscripcion(Long id, InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException;
}
