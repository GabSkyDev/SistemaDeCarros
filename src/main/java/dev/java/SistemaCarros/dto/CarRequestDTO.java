package dev.java.SistemaCarros.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Year;
import java.util.Objects;
@Schema(description = "Objeto utilizado para criar ou atualizar dados de um carro")
public record CarRequestDTO (
        @Schema(description = "ID do carro (usado em atualizações)", example = "10")
        Long id,

        @Schema(description = "Placa do veículo no formato ABC-1234", example = "ABC-1234", required = true)
        String placa,

        @Schema(description = "Modelo do carro", example = "Civic", required = true)
        String modelo,

        @Schema(description = "Marca do carro", example = "Honda", required = true)
        String marca,

        @Schema(description = "Ano de fabricação do veículo", example = "2022", required = true)
        Integer anoFabricacao,

        @Schema(description = "Cor do carro", example = "Prata")
        String cor,

        @Schema(description = "Tipo de combustível utilizado", example = "Gasolina")
        String tipoCombustivel,

        @Schema(description = "Tipo de transmissão", example = "Automática")
        String transmissao,

        @Schema(description = "CPF do usuário dono do carro", example = "123.456.789-00", required = true)
        String cpfUsuario
    ){
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
