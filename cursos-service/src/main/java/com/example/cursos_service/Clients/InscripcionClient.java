package com.example.cursos_service.Clients;

import com.example.cursos_service.Config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inscripciones-service",configuration = FeignConfig.class)
public interface InscripcionClient {

    @GetMapping("/api/inscripciones/exists/{cursoId}")
    Boolean existsByUserIdAndCursoId(@PathVariable Long cursoId,@RequestParam String userId);
}

