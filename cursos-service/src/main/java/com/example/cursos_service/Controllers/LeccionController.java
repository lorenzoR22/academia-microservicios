package com.example.cursos_service.Controllers;

import com.example.cursos_service.Exceptions.NotFoundLeccionException;
import com.example.dtos.leccion.LeccionRequestDTO;
import com.example.dtos.leccion.LeccionResponseDTO;
import com.example.cursos_service.Exceptions.CursoNotFoundException;
import com.example.cursos_service.Exceptions.WithoutInscripcionException;
import com.example.cursos_service.Services.Impl.LeccionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos/{cursoId}/lecciones")
@RequiredArgsConstructor
public class LeccionController {
    private final LeccionServiceImpl leccionService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeccionResponseDTO getLeccion(@PathVariable("id")Long id,
                                         @AuthenticationPrincipal Jwt token) throws WithoutInscripcionException, CursoNotFoundException {
        String id_user=token.getClaim("sub");
        return leccionService.getLeccion(id,id_user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public LeccionResponseDTO saveLeccion(@PathVariable Long cursoId,@Valid @RequestBody LeccionRequestDTO leccion) throws CursoNotFoundException {
        return leccionService.saveLeccion(cursoId,leccion);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public LeccionResponseDTO updateLeccion(@PathVariable Long id,@Valid @RequestBody LeccionRequestDTO request) throws NotFoundLeccionException {
        return leccionService.updateLeccion(id,request);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public LeccionResponseDTO updateParcialLeccion(@PathVariable Long id,@Valid @RequestBody LeccionRequestDTO request) throws NotFoundLeccionException {
        return leccionService.updateParcialLeccion(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLeccion(@PathVariable Long id){
        leccionService.deleteLeccion(id);
    }

}
