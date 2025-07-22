package dev.java.SistemaCarros.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Objeto de resposta contendo os dados completos de um serviço realizado")
public record TaskResponseDTO (
        @Schema(description = "Descrição detalhada do serviço realizado", example = "Troca de óleo e filtro")
        String descricao,

        @Schema(description = "Data em que o serviço foi realizado", example = "2024-06-15")
        LocalDate dataServico,

        @Schema(description = "Valor pago pelo serviço", example = "250.00")
        BigDecimal valorPago,

        @Schema(description = "ID da mecânica responsável pelo serviço", example = "5")
        Long mecanicaId,

        @Schema(description = "Nome da oficina responsável pelo serviço", example = "Oficina do João")
        String mecanicaNome,

        @Schema(description = "ID do carro que recebeu o serviço", example = "12")
        Long carroId,

        @Schema(description = "Placa do veículo que recebeu o serviço", example = "ABC-1234")
        String placaCarro,

        @Schema(description = "Modelo do carro que recebeu o serviço", example = "Civic")
        String modeloCarro
){}
