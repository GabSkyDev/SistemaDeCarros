package dev.java.SistemaCarros.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Schema(description = "Objeto utilizado para criar ou atualizar dados de um serviço realizado")
public record TaskRequestDTO (
        @Schema(description = "ID do serviço (usado em atualizações)", example = "1")
        Long id,

        @Schema(description = "Descrição detalhada do serviço realizado", example = "Troca de óleo e filtro", required = true)
        String descricao,

        @Schema(description = "Data em que o serviço foi realizado", example = "2024-06-15", required = true)
        LocalDate dataServico,

        @Schema(description = "Valor pago pelo serviço", example = "250.00", required = true)
        BigDecimal valorPago,

        @Schema(description = "ID da mecânica responsável pelo serviço", example = "5", required = true)
        Long mecanicaId,

        @Schema(description = "ID do carro que recebeu o serviço", example = "12", required = true)
        Long carroId){
    public TaskRequestDTO {
        // Validação de descrição
        Objects.requireNonNull(descricao, "Descrição não pode ser nula");
        if (descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode estar vazia");
        }
        if (descricao.length() > 500) {
            throw new IllegalArgumentException("Descrição não pode exceder 500 caracteres");
        }

        // Validação de data
        Objects.requireNonNull(dataServico, "Data do serviço não pode ser nula");
        if (dataServico.isAfter(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("Data do serviço não pode ser no futuro");
        }
        if (dataServico.isBefore(LocalDate.of(2000, 1, 1))) {
            throw new IllegalArgumentException("Data do serviço muito antiga");
        }

        // Validação de valor
        Objects.requireNonNull(valorPago, "Valor pago não pode ser nulo");
        if (valorPago.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor pago deve ser positivo");
        }
        if (valorPago.compareTo(new BigDecimal("1000000")) > 0) {
            throw new IllegalArgumentException("Valor pago excede o limite máximo");
        }
    }
}
