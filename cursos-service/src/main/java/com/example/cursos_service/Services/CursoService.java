package com.example.cursos_service.Services;

import com.example.dtos.curso.CursoRequestDTO;
import com.example.dtos.curso.CursoResponseDTO;
import com.example.cursos_service.Exceptions.CursoNotFoundException;

public interface CursoService {
    CursoResponseDTO getCurso(Long id) throws CursoNotFoundException;
    CursoResponseDTO saveCurso(CursoRequestDTO dto);
    CursoResponseDTO updateCurso(Long id,CursoRequestDTO request) throws CursoNotFoundException;
    CursoResponseDTO updateParcialCurso(Long id, CursoRequestDTO request) throws CursoNotFoundException;
    void deleteCurso(Long id);
}
