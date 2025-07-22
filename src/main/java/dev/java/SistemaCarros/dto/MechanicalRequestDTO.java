package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.OperatingHours;
import dev.java.SistemaCarros.model.Task;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "Objeto utilizado para criar ou atualizar dados de uma mecânica")
public record MechanicalRequestDTO (
        @Schema(description = "ID da mecânica (usado em atualizações)", example = "5")
        Long id,

        @Schema(description = "CNPJ da oficina ou mecânica", example = "12.345.678/0001-99", required = true)
        String cnpj,

        @Schema(description = "Nome da oficina ou do mecânico", example = "Oficina do João", required = true)
        String nome,

        @Schema(description = "Endereço completo da oficina", example = "Rua das Oficinas, 123", required = true)
        String endereco,

        @Schema(description = "Email de contato", example = "contato@oficinajoao.com", required = true)
        String email,

        @Schema(description = "Telefone de contato", example = "(11) 98765-4321", required = true)
        String telefone,

        @Schema(description = "Lista de especialidades da oficina", example = "[\"Motor\", \"Freios\", \"Suspensão\"]", required = true)
        List<String> especialidades,

        @Schema(description = "Horários de funcionamento cadastrados para oficina", required = true)
        List<OperatingHours> horarios,

        @Schema(description = "Serviços (tarefas) que a mecânica realizou")
        List<Task> servicos
    ){

    public MechanicalRequestDTO(Long id, String cnpj, String nome, String endereco, String email, String telefone) {
        this(id, cnpj, nome, endereco, email, telefone, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public MechanicalRequestDTO {
        // Validação de nulidades
        Objects.requireNonNull(nome,"Nome não pode ser nulo");
        Objects.requireNonNull(endereco,"Endereço não pode ser nulo");
        Objects.requireNonNull(email,"Telefone não pode ser nulo");
        Objects.requireNonNull(telefone,"Especialidade não pode ser nulo");

    }
}
