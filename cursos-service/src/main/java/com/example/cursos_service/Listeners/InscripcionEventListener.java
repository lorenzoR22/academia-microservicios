package com.example.cursos_service.Listeners;

import com.example.cursos_service.Services.Impl.InscripcionService;
import com.example.events.InscripcionEvent;
import com.example.utils.Jsonutils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InscripcionEventListener {

    private final InscripcionService inscripcionService;

    @KafkaListener(topics = "inscripcion-topic")
    public void handleInscripcionSaved(String message){
        InscripcionEvent inscripcionEvent= Jsonutils.fromJson(message, InscripcionEvent.class);
        inscripcionService.saveInscripcion(inscripcionEvent.id_user(),inscripcionEvent.id_curso());
    }
}
