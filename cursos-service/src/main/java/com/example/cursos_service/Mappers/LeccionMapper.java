package com.example.cursos_service.Mappers;

import com.example.dtos.leccion.LeccionRequestDTO;
import com.example.dtos.leccion.LeccionResponseDTO;
import com.example.cursos_service.Entities.Leccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeccionMapper {

    @Mapping(target = "cursoId", source = "curso.id") // del objeto curso obtenemos el id
    LeccionResponseDTO toDTO(Leccion leccion);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "creacion",ignore = true)
    @Mapping(target = "ultimoUpdate",ignore = true)
    @Mapping(target = "curso", ignore = true)
    Leccion toEntity(LeccionRequestDTO dto);
}
