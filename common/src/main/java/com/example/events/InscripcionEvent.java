package com.example.events;

public record InscripcionEvent(
        Long id_curso
        ,String id_user
        ,String email
        ,String titulo_curso){
}
