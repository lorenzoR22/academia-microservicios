package com.example.inscripciones_service.Services;


import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.inscripcion.InscripcionResponseDTO;
import com.example.dtos.inscripcion.InscripcionUpdateRequestDTO;
import com.example.inscripciones_service.Exceptions.InscripcionNotFoundException;

public interface InscripcionService {
    InscripcionResponseDTO getInscripcion(Long id) throws InscripcionNotFoundException;
    Boolean existByUserIdAndCursoId(String id_user,Long curso_id);
    InscripcionResponseDTO saveInscripcion(InscripcionRequestDTO dto,String email,String titulo_curso);
    InscripcionResponseDTO updateInscripcion(Long id, InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException;
    InscripcionResponseDTO updateParcialInscripcion(Long id, InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException;
}
