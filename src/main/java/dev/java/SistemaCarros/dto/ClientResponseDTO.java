package dev.java.SistemaCarros.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta contendo os dados completos de um cliente")
public record ClientResponseDTO (
        @Schema(description = "CPF do cliente no formato 000.000.000-00", example = "123.456.789-00")
        String cpf,

        @Schema(description = "Nome completo do cliente", example = "Gabriel Sousa")
        String nome,

        @Schema(description = "Endereço de e-mail do cliente", example = "gabriel@email.com")
        String email,

        @Schema(description = "Telefone de contato", example = "(11) 91234-5678")
        String telefone
    ){}
