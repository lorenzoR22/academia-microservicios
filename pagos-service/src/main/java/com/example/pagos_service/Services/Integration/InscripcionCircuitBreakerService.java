package com.example.pagos_service.Services.Integration;

import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.exceptions.ServicioNoDisponibleException;
import com.example.pagos_service.Clients.InscripcionClient;
import com.example.exceptions.inscripciones.AlreadyHaveInscripcionException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InscripcionCircuitBreakerService {

    private final InscripcionClient inscripcionClient;

    public void saveInscripcion(InscripcionRequestDTO inscripcionRequestDTO, String email, String titulo_curso) {
        try {
            trySaveInscripcion(inscripcionRequestDTO, email, titulo_curso);
        } catch (FeignException.Conflict ex) {
            throw new AlreadyHaveInscripcionException(titulo_curso);
        }
    }

    @CircuitBreaker(name = "inscripciones-service", fallbackMethod = "fallbackSaveInscripcion")
    private void trySaveInscripcion(InscripcionRequestDTO inscripcionRequestDTO, String email, String titulo_curso) {
        inscripcionClient.saveInscripcion(inscripcionRequestDTO, email, titulo_curso);
    }

    public void fallbackSaveInscripcion(InscripcionRequestDTO inscripcionRequestDTO,String email,String titulo_curso,Throwable ex){
        throw new ServicioNoDisponibleException("inscripciones-service",ex);
    }
}
