package com.example.pagos_service.Clients;

import com.example.dtos.curso.CursoResponseDTO;
import com.example.pagos_service.Config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "cursos-service",configuration = FeignConfig.class)
public interface CursoClient {

    @GetMapping("/api/cursos/{id}")
    CursoResponseDTO getCurso(@PathVariable Long id);

}
