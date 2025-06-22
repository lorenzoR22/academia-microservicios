package com.example.cursos_service.Services.Impl;


import com.example.cursos_service.Exceptions.NotFoundLeccionException;
import com.example.cursos_service.Exceptions.ServicioNoDisponibleException;
import com.example.dtos.leccion.LeccionRequestDTO;
import com.example.dtos.leccion.LeccionResponseDTO;
import com.example.cursos_service.Clients.InscripcionClient;
import com.example.cursos_service.Entities.Curso;
import com.example.cursos_service.Entities.Leccion;
import com.example.cursos_service.Exceptions.CursoNotFoundException;
import com.example.cursos_service.Exceptions.WithoutInscripcionException;
import com.example.cursos_service.Mappers.LeccionMapper;
import com.example.cursos_service.Repositories.CursoRepository;
import com.example.cursos_service.Repositories.LeccionRepository;
import com.example.cursos_service.Services.LeccionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LeccionServiceImpl implements LeccionService {

    private final LeccionRepository leccionRepository;
    private final LeccionMapper leccionMapper;
    private final CursoRepository cursoRepository;
    private final InscripcionClient inscripcionClient;

    @CircuitBreaker(name = "inscripciones-service", fallbackMethod = "fallbackGetLeccion")
    public LeccionResponseDTO getLeccion(Long id,String id_user) throws CursoNotFoundException, WithoutInscripcionException {
        Leccion leccion=leccionRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException(id));

        Boolean inscripto = inscripcionClient.existsByUserIdAndCursoId(leccion.getCurso().getId(), id_user);
        if (!inscripto) {
            throw new WithoutInscripcionException(leccion.getCurso().getTitulo(), id_user);
        }

        return leccionMapper.toDTO(leccion);
    }
    public LeccionResponseDTO fallbackGetLeccion(Long id, String id_user, Throwable ex)  {
        // Manejo cuando inscripciones falla completamente (por timeout, etc.)
        throw new ServicioNoDisponibleException("inscripciones-service",ex);
    }

    public LeccionResponseDTO saveLeccion(Long cursoId, LeccionRequestDTO dto) throws CursoNotFoundException {
        Curso curso=cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException(cursoId));
        Leccion leccion = leccionMapper.toEntity(dto);
        leccion.setCurso(curso);
        Leccion newLeccion = leccionRepository.save(leccion);
        return leccionMapper.toDTO(newLeccion);
    }

    public LeccionResponseDTO updateLeccion(Long leccionId,LeccionRequestDTO request) throws NotFoundLeccionException {
        Leccion leccion=leccionRepository.findById(leccionId)
                .orElseThrow(() -> new NotFoundLeccionException(leccionId));

        leccion.setTitulo(request.getTitulo());
        leccion.setContenido(request.getContenido());
        leccion.setOrden(request.getOrden());

        leccionRepository.save(leccion);

        return leccionMapper.toDTO(leccion);
    }

    public LeccionResponseDTO updateParcialLeccion(Long leccionId, LeccionRequestDTO request) throws NotFoundLeccionException {
        Leccion leccion = leccionRepository.findById(leccionId)
                .orElseThrow(() -> new NotFoundLeccionException(leccionId));

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
