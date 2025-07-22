package dev.java.SistemaCarros.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta contendo os dados completos de um carro")
public record CarResponseDTO (
        @Schema(description = "Placa do veículo no formato ABC-1234", example = "ABC-1234")
        String placa,

        @Schema(description = "Modelo do carro", example = "Civic")
        String modelo,

        @Schema(description = "Marca do carro", example = "Honda")
        String marca,

        @Schema(description = "Ano de fabricação do veículo", example = "2022")
        Integer anoFabricacao,

        @Schema(description = "Cor do carro", example = "Prata")
        String cor,

        @Schema(description = "Tipo de combustível utilizado", example = "Gasolina")
        String tipoCombustivel,

        @Schema(description = "Tipo de transmissão", example = "Automática")
        String transmissao,

        @Schema(description = "CPF do usuário dono do carro", example = "123.456.789-00")
        String cpfUsuario
    ){}
