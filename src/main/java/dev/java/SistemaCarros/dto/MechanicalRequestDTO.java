package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.OperatingHours;
import dev.java.SistemaCarros.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record MechanicalRequestDTO (
        Long id,
        String cnpj,
        String nome,
        String endereco,
        String email,
        String telefone,
        List<String> especialidades,
        List<OperatingHours> horarios,
        List<Task> servicos){

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
