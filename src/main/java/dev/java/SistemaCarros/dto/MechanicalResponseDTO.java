package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.OperatingHours;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Objeto de resposta contendo os dados completos de uma mecânica")
public record MechanicalResponseDTO (
        @Schema(description = "CNPJ da oficina ou mecânica", example = "12.345.678/0001-99")
        String cnpj,

        @Schema(description = "Nome da oficina ou do mecânico", example = "Oficina do João")
        String nome,

        @Schema(description = "Endereço completo da oficina", example = "Rua das Oficinas, 123")
        String endereco,

        @Schema(description = "Email de contato", example = "contato@oficinajoao.com")
        String email,

        @Schema(description = "Telefone de contato", example = "(11) 98765-4321")
        String telefone,

        @Schema(description = "Lista de especialidades da oficina", example = "[\"Motor\", \"Freios\", \"Suspensão\"]")
        List<String> especialidades,

        @Schema(description = "Horários de funcionamento cadastrados para oficina")
        List<OperatingHours> horarios
    ){}
