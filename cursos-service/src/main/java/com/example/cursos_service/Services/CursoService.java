package com.example.cursos_service.Services;

import com.example.dtos.curso.CursoRequestDTO;
import com.example.dtos.curso.CursoResponseDTO;

public interface CursoService {
    CursoResponseDTO getCurso(Long id);
    CursoResponseDTO saveCurso(CursoRequestDTO dto);
    CursoResponseDTO updateCurso(Long id,CursoRequestDTO request);
    CursoResponseDTO updateParcialCurso(Long id, CursoRequestDTO request);
    void deleteCurso(Long id);
}
