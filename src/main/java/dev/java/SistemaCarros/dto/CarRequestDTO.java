package dev.java.SistemaCarros.dto;

import java.time.Year;
import java.util.Objects;

public record CarRequestDTO (
        Long id,
        String placa,
        String modelo,
        String marca,
        Integer anoFabricacao,
        String cor,
        String tipoCombustivel,
        String transmissao,
        String cpfUsuario){
    public CarRequestDTO {
        // Validação de nulidade
        Objects.requireNonNull(placa, "Placa do veículo não pode ser nula.");
        Objects.requireNonNull(modelo, "Modelo do veículo não pode ser nulo.");
        Objects.requireNonNull(marca, "Marca do veículo não pode ser nula.");

        // Validação de placa
        if (!placa.matches("[A-Z]{3}-?\\d{4}")){
            throw new IllegalArgumentException("Formato de placa inválido.");
        }

        // Validação de ano
        if (anoFabricacao < 1886 || anoFabricacao > Year.now().getValue() + 1){
            throw new IllegalArgumentException("Ano de veículo inválido.");
        }

        // Validação de CPF
        if (!cpfUsuario.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")){
            throw new IllegalArgumentException("Formato de CPF inválido.");
        }

    }

}
