package dev.java.SistemaCarros.dto;

public record ClientResponseDTO (
        String cpf,
        String nome,
        String email,
        String telefone){}
