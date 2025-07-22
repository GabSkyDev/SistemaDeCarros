package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record ClientRequestDTO (
        Long id,
        String cpf,
        String nome,
        String email,
        String telefone,
        List<Car> carrosRegistrados){
    public ClientRequestDTO(Long id, String cpf, String nome, String email, String telefone) {
        this(id, cpf, nome, email, telefone, new ArrayList<>());

    }

    public ClientRequestDTO {
        Objects.requireNonNull(nome, "Nome não pode ser nulo.");

        // Validação de CPF
        if (cpf != null && !cpf.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")) {
            throw new IllegalArgumentException("Formato de CPF inválido.");
        }
    }
}
