package com.example.inscripciones_service.Controllers;


import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.inscripcion.InscripcionResponseDTO;
import com.example.dtos.inscripcion.InscripcionUpdateRequestDTO;
import com.example.inscripciones_service.Exceptions.InscripcionNotFoundException;
import com.example.inscripciones_service.Services.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {
    private final InscripcionService inscripcionService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InscripcionResponseDTO getInscripcion(@PathVariable Long id) throws InscripcionNotFoundException {
        return inscripcionService.getInscripcion(id);
    }

    @GetMapping("/exists/{cursoId}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean existsByUserIdAndCursoId(@PathVariable Long cursoId, String userId){
        return inscripcionService.existByUserIdAndCursoId(userId,cursoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('SERVICIO')")
    public InscripcionResponseDTO saveInscripcion(@Valid @RequestBody InscripcionRequestDTO dto,String email,String titulo_curso){
        return inscripcionService.saveInscripcion(dto,email,titulo_curso);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public InscripcionResponseDTO updateInscripcion(@PathVariable Long id,@Valid @RequestBody InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException {
        return inscripcionService.updateInscripcion(id,request);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public InscripcionResponseDTO updateParcialInscripcion(@PathVariable Long id,@Valid @RequestBody InscripcionUpdateRequestDTO request) throws InscripcionNotFoundException {
        return inscripcionService.updateParcialInscripcion(id,request);
    }
}
