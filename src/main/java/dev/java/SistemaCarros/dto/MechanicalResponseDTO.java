package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.OperatingHours;

import java.util.List;

public record MechanicalResponseDTO (
        String cnpj,
        String nome,
        String endereco,
        String email,
        String telefone,
        List<String> especialidades,
        List<OperatingHours> horarios
){}
