package com.example.inscripciones_service.Services.Impl;

import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.inscripcion.InscripcionResponseDTO;
import com.example.dtos.inscripcion.InscripcionUpdateRequestDTO;
import com.example.inscripciones_service.Entities.Inscripcion;
import com.example.inscripciones_service.Exceptions.InscripcionNotFoundException;
import com.example.inscripciones_service.Mappers.InscripcionMapper;
import com.example.inscripciones_service.Repositories.InscripcionRepository;
import com.example.inscripciones_service.Services.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final InscripcionMapper inscripcionMapper;

    public InscripcionResponseDTO getInscripcion(Long id) throws InscripcionNotFoundException {
        Inscripcion inscripcion=inscripcionRepository.findById(id)
                .orElseThrow(()->new InscripcionNotFoundException(id));

        return inscripcionMapper.toDTO(inscripcion);
    }

    public Boolean existByUserIdAndCursoId(String id_user,Long curso_id){
        return inscripcionRepository.existsByUsuarioIdAndCursoId(id_user,curso_id);
    }

    public InscripcionResponseDTO saveInscripcion(String userId, InscripcionRequestDTO dto){
        Inscripcion inscripcion=inscripcionMapper.toEntity(dto);
        inscripcion.setUsuarioId(userId);
        Inscripcion saved=inscripcionRepository.save(inscripcion);
        return inscripcionMapper.toDTO(saved);
    }

    public InscripcionResponseDTO updateInscripcion(Long id, InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException {
        Inscripcion inscripcion=inscripcionRepository.findById(id)
                .orElseThrow(()->new InscripcionNotFoundException(id));

        inscripcion.setProgreso(request.getProgreso());
        inscripcion.setEstado(request.getEstado());

        inscripcionRepository.save(inscripcion);

        return inscripcionMapper.toDTO(inscripcion);
    }

    public InscripcionResponseDTO updateParcialInscripcion(Long id, InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(()->new InscripcionNotFoundException(id));

        if (request.getProgreso() != null) {
            inscripcion.setProgreso(request.getProgreso());
        }

        if (request.getEstado() != null) {
            inscripcion.setEstado(request.getEstado());
        }

        inscripcionRepository.save(inscripcion);

        return inscripcionMapper.toDTO(inscripcion);
    }

}
