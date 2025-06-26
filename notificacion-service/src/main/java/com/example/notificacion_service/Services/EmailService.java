package com.example.notificacion_service.Services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarConfirmacion(String destinatario, String nombreCurso) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Inscripción confirmada");
        mensaje.setText("¡Hola! Te has inscrito exitosamente al curso: " + nombreCurso);
        mailSender.send(mensaje);
    }
}