package dev.java.SistemaCarros.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record TaskRequestDTO (
        Long id,
        String descricao,
        LocalDate dataServico,
        BigDecimal valorPago,
        Long mecanicaId,
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
