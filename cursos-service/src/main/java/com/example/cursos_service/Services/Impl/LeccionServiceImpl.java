package com.example.cursos_service.Services.Impl;


import com.example.cursos_service.Exceptions.LeccionNotFoundException;
import com.example.dtos.leccion.LeccionRequestDTO;
import com.example.dtos.leccion.LeccionResponseDTO;
import com.example.cursos_service.Entities.Curso;
import com.example.cursos_service.Entities.Leccion;
import com.example.cursos_service.Exceptions.WithoutInscripcionException;
import com.example.cursos_service.Mappers.LeccionMapper;
import com.example.cursos_service.Repositories.CursoRepository;
import com.example.cursos_service.Repositories.LeccionRepository;
import com.example.cursos_service.Services.LeccionService;
import com.example.exceptions.cursos.CursoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LeccionServiceImpl implements LeccionService {

    private final LeccionRepository leccionRepository;
    private final LeccionMapper leccionMapper;
    private final CursoRepository cursoRepository;
    private final InscripcionService inscripcionService;


    public LeccionResponseDTO getLeccion(Long id,String id_user) throws WithoutInscripcionException, LeccionNotFoundException {
        Leccion leccion=leccionRepository.findById(id)
                .orElseThrow(() -> new LeccionNotFoundException(id));

        Boolean inscripto = inscripcionService.existsInscripcion(id_user,id);
        if (!inscripto) {
            throw new WithoutInscripcionException(leccion.getCurso().getTitulo(), id_user);
        }

        return leccionMapper.toDTO(leccion);
    }

    public LeccionResponseDTO saveLeccion(Long cursoId, LeccionRequestDTO dto) throws CursoNotFoundException {
        Curso curso=cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException(cursoId));
        Leccion leccion = leccionMapper.toEntity(dto);
        leccion.setCurso(curso);
        Leccion newLeccion = leccionRepository.save(leccion);
        return leccionMapper.toDTO(newLeccion);
    }

    public LeccionResponseDTO updateLeccion(Long leccionId,LeccionRequestDTO request) throws LeccionNotFoundException {
        Leccion leccion=leccionRepository.findById(leccionId)
                .orElseThrow(() -> new LeccionNotFoundException(leccionId));

        leccion.setTitulo(request.getTitulo());
        leccion.setContenido(request.getContenido());
        leccion.setOrden(request.getOrden());

        leccionRepository.save(leccion);

        return leccionMapper.toDTO(leccion);
    }

    public LeccionResponseDTO updateParcialLeccion(Long leccionId, LeccionRequestDTO request) throws LeccionNotFoundException {
        Leccion leccion = leccionRepository.findById(leccionId)
                .orElseThrow(() -> new LeccionNotFoundException(leccionId));

        if (request.getTitulo() != null) {
            leccion.setTitulo(request.getTitulo());
        }

        if (request.getContenido() != null) {
            leccion.setContenido(request.getContenido());
        }

        if (request.getOrden() != null) {
            leccion.setOrden(request.getOrden());
        }

        leccionRepository.save(leccion);

        return leccionMapper.toDTO(leccion);
    }

    public void deleteLeccion(Long id){
        leccionRepository.deleteById(id);
    }

}
