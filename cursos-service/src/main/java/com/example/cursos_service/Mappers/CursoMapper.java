package com.example.cursos_service.Mappers;


import com.example.dtos.curso.CursoRequestDTO;
import com.example.dtos.curso.CursoResponseDTO;
import com.example.cursos_service.Entities.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoResponseDTO toDTO(Curso curso);

    @Mapping(target = "creacion", ignore = true)
    @Mapping(target = "ultimoUpdate", ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target ="lecciones",ignore = true)
    Curso toEntity(CursoRequestDTO dto);
}
