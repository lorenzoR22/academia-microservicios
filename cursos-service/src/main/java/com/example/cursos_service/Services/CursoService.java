package com.example.cursos_service.Services;

import com.example.dtos.curso.CursoRequestDTO;
import com.example.dtos.curso.CursoResponseDTO;
import com.example.cursos_service.Exceptions.NotFoundException;

public interface CursoService {
    CursoResponseDTO getCurso(Long id) throws NotFoundException;
    CursoResponseDTO saveCurso(CursoRequestDTO dto);
    CursoResponseDTO updateCurso(Long id,CursoRequestDTO request) throws NotFoundException;
    CursoResponseDTO updateParcialCurso(Long id, CursoRequestDTO request) throws NotFoundException;
    void deleteCurso(Long id);
}
