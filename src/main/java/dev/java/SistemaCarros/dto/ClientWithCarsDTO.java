package dev.java.SistemaCarros.dto;

import java.util.List;

public record ClientWithCarsDTO (
        Long id,
        String cpf,
        String nome,
        String email,
        String telefone,
        List<CarRequestDTO> carrosRegistrados
){}
