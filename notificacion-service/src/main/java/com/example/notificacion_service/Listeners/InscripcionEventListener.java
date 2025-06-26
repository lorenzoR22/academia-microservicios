package com.example.notificacion_service.Listeners;

import com.example.events.InscripcionEvent;
import com.example.notificacion_service.Services.EmailService;
import com.example.utils.Jsonutils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InscripcionEventListener {

    private final EmailService emailService;

    public InscripcionEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "inscripcion-topic")
    public void handleInscripcionNotification(String message){
        InscripcionEvent inscripcionEvent= Jsonutils.fromJson(message, InscripcionEvent.class);
        emailService.enviarConfirmacion(inscripcionEvent.email(), inscripcionEvent.titulo_curso());
    }
}
