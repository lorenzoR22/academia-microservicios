package com.example.pagos_service.Services.Integration;

import com.example.dtos.curso.CursoResponseDTO;
import com.example.exceptions.ServicioNoDisponibleException;
import com.example.pagos_service.Clients.CursoClient;
import com.example.exceptions.cursos.CursoNotFoundException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoCircuitBreakerService {

    private final CursoClient cursoClient;

    public CursoResponseDTO getCurso(Long idCurso) {
        try {
            return tryGetCurso(idCurso);
        } catch (FeignException.NotFound e) {
            throw new CursoNotFoundException(idCurso);
        }
    }

    @CircuitBreaker(name = "cursos-service", fallbackMethod = "fallbackGetCurso")
    private CursoResponseDTO tryGetCurso(Long idCurso) {
        return cursoClient.getCurso(idCurso);
    }

    public CursoResponseDTO fallbackGetCurso(Long idCurso, Throwable ex) {
        throw new ServicioNoDisponibleException("cursos-service", ex);
    }
}
