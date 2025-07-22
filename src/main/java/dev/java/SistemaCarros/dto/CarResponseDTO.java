package dev.java.SistemaCarros.dto;

public record CarResponseDTO (
        String placa,
        String modelo,
        String marca,
        Integer anoFabricacao,
        String cor,
        String tipoCombustivel,
        String transmissao,
        String cpfUsuario
    ){}
