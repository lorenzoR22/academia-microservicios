package com.example.cursos_service.Services.Impl;


import com.example.dtos.curso.CursoRequestDTO;
import com.example.dtos.curso.CursoResponseDTO;
import com.example.cursos_service.Entities.Curso;
import com.example.cursos_service.Exceptions.CursoNotFoundException;
import com.example.cursos_service.Mappers.CursoMapper;
import com.example.cursos_service.Repositories.CursoRepository;
import com.example.cursos_service.Services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoResponseDTO getCurso(Long id) throws CursoNotFoundException {
        Curso curso=cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException(id));
        return cursoMapper.toDTO(curso);
    }

    public CursoResponseDTO saveCurso(CursoRequestDTO dto){
        Curso newCurso=cursoMapper.toEntity(dto);
        Curso savedCurso=cursoRepository.save(newCurso);
        return cursoMapper.toDTO(savedCurso);
    }

    public CursoResponseDTO updateCurso(Long id,CursoRequestDTO request) throws CursoNotFoundException {
        Curso curso=cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException(id));

        curso.setTitulo(request.getTitulo());
        curso.setDescripcion(request.getDescripcion());
        curso.setCategoria(request.getCategoria());
        curso.setProfesor(request.getProfesor());
        curso.setPrecio(request.getPrecio());
        curso.setEstado(request.getEstado());

        cursoRepository.save(curso);

        return cursoMapper.toDTO(curso);
    }

    public CursoResponseDTO updateParcialCurso(Long id, CursoRequestDTO request) throws CursoNotFoundException {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException(id));

        if (request.getTitulo() != null) {
            curso.setTitulo(request.getTitulo());
        }

        if (request.getDescripcion() != null) {
            curso.setDescripcion(request.getDescripcion());
        }

        if (request.getCategoria() != null) {
            curso.setCategoria(request.getCategoria());
        }

        if (request.getProfesor() != null) {
            curso.setProfesor(request.getProfesor());
        }

        if (request.getPrecio() != null) {
            curso.setPrecio(request.getPrecio());
        }

        if (request.getEstado() != null) {
            curso.setEstado(request.getEstado());
        }

        cursoRepository.save(curso);

        return cursoMapper.toDTO(curso);
    }
    public void deleteCurso(Long id){
        cursoRepository.deleteById(id);
    }


}
