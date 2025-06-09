package com.example.dtos.usuario;

import java.time.LocalDate;


public class PerfilUsuarioRequestDTO {
    private String nombreCompleto;
    private String avatarUrl;
    private String bio;
    private LocalDate cumpleanos;

    public PerfilUsuarioRequestDTO(String nombreCompleto, String avatarUrl, String bio, LocalDate cumpleanos) {
        this.nombreCompleto = nombreCompleto;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.cumpleanos = cumpleanos;
    }

    public PerfilUsuarioRequestDTO() {
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getCumpleanos() {
        return cumpleanos;
    }

    public void setCumpleanos(LocalDate cumpleanos) {
        this.cumpleanos = cumpleanos;
    }
}
