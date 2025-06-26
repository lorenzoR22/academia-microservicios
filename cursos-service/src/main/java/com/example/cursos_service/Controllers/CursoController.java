package com.example.cursos_service.Controllers;


import com.example.dtos.curso.CursoRequestDTO;
import com.example.dtos.curso.CursoResponseDTO;
import com.example.cursos_service.Services.CursoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CursoResponseDTO getCurso(@PathVariable("id") Long id) {
        return cursoService.getCurso(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @CircuitBreaker(name = "cursos-service",fallbackMethod = "saveCursoFallBack")
    public ResponseEntity<CursoResponseDTO> saveCurso(@Valid @RequestBody CursoRequestDTO curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.saveCurso(curso));
    }

    private ResponseEntity<CursoResponseDTO>saveCursoFallBack(CursoResponseDTO curso,Throwable throwable){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public CursoResponseDTO updateCurso(@PathVariable Long id,@Valid @RequestBody CursoRequestDTO request) {
        return cursoService.updateCurso(id,request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public CursoResponseDTO updateParcialCurso(@PathVariable Long id,@Valid @RequestBody CursoRequestDTO request)  {
        return cursoService.updateParcialCurso(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCurso(@PathVariable Long id){
        cursoService.deleteCurso(id);
    }
}