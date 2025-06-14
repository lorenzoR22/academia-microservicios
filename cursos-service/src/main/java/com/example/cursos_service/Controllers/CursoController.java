package com.example.cursos_service.Controllers;


import com.example.dtos.curso.CursoRequestDTO;
import com.example.dtos.curso.CursoResponseDTO;
import com.example.cursos_service.Exceptions.CursoNotFoundException;
import com.example.cursos_service.Services.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CursoResponseDTO getCurso(@PathVariable("id") Long id) throws CursoNotFoundException {
        return cursoService.getCurso(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CursoResponseDTO saveCurso(@Valid @RequestBody CursoRequestDTO curso){
        return cursoService.saveCurso(curso);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public CursoResponseDTO updateCurso(@PathVariable Long id,@Valid @RequestBody CursoRequestDTO request) throws CursoNotFoundException {
        return cursoService.updateCurso(id,request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public CursoResponseDTO updateParcialCurso(@PathVariable Long id,@Valid @RequestBody CursoRequestDTO request) throws CursoNotFoundException {
        return cursoService.updateParcialCurso(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCurso(@PathVariable Long id){
        cursoService.deleteCurso(id);
    }
}