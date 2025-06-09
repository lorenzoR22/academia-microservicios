package com.example.pagos_service.Clients;

import com.example.dtos.inscripcion.InscripcionRequestDTO;
import com.example.dtos.inscripcion.InscripcionResponseDTO;
import com.example.pagos_service.Config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inscripciones-service",configuration = FeignConfig.class)

public interface InscripcionClient {

    @PostMapping("/api/inscripciones")
    void saveInscripcion(@RequestBody InscripcionRequestDTO dto, @RequestParam String id_user);

    @GetMapping("/api/inscripciones/{id}")
    InscripcionResponseDTO get(@PathVariable Long id);
}
