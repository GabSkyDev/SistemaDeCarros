package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.OperatingHours;

import java.util.List;

public class MechanicalResponseDTO {
    private String cnpj;
    private String nome;
    private String endereco;
    private String email;
    private String telefone;
    private List<String> especialidades;
    private List<OperatingHours> horarios;
    public MechanicalResponseDTO() {

    }

    public MechanicalResponseDTO(String cnpj, String nome, String endereco, String email, String telefone, List<String> especialidades, List<OperatingHours> horarios) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.especialidades = especialidades;
        this.horarios = horarios;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }

    public List<OperatingHours> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<OperatingHours> horarios) {
        this.horarios = horarios;
    }
}
