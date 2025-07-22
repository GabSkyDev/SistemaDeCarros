package dev.java.SistemaCarros.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TaskResponseDTO (
        String descricao,
        LocalDate dataServico,
        BigDecimal valorPago,
        Long mecanicaId,
        String mecanicaNome,
        Long carroId,
        String placaCarro,
        String modeloCarro
){}
