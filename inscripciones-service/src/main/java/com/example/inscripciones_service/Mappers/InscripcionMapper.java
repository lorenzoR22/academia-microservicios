package com.example.inscripciones_service.Mappers;

import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.inscripcion.InscripcionResponseDTO;
import com.example.inscripciones_service.Entities.Inscripcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {

    InscripcionResponseDTO toDTO(Inscripcion inscripcion);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "fechaInscripcion",ignore = true)
    @Mapping(target = "estado",ignore = true)
    @Mapping(target = "progreso",ignore = true)
    @Mapping(target = "usuarioId", ignore = true)
    Inscripcion toEntity(InscripcionRequestDTO dto);
}
