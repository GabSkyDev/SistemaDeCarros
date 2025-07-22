package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.Car;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Schema(description = "Objeto utilizado para criar ou atualizar dados de um cliente")
public record ClientRequestDTO (
        @Schema(description = "ID do cliente (usado em atualizações)", example = "1")
        Long id,

        @Schema(description = "CPF do cliente no formato 000.000.000-00", example = "123.456.789-00", required = true)
        String cpf,

        @Schema(description = "Nome completo do cliente", example = "Gabriel Sousa", required = true)
        String nome,

        @Schema(description = "Endereço de e-mail do cliente", example = "gabriel@email.com", required = true)
        String email,

        @Schema(description = "Telefone de contato", example = "(11) 91234-5678", required = true)
        String telefone,

        @Schema(description = "Lista de carros já registrados para o cliente")
        List<CarRequestDTO> carrosRegistrados
    ){
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
