package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.HorarioFuncionamento;
import dev.java.SistemaCarros.model.Servico;

import java.util.List;

public class MecanicaRequestDTO {
    private Long id;
    private String cnpj;
    private String nome;
    private String endereco;
    private String email;
    private String telefone;
    private List<String> especialidades;
    private List<HorarioFuncionamento> horarios;
    private List<Servico> servicos;

    public MecanicaRequestDTO(){

    }

    public MecanicaRequestDTO(Long id, String cnpj, String nome, String endereco, String email, String telefone, List<String> especialidades, List<HorarioFuncionamento> horarios, List<Servico> servicos) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.especialidades = especialidades;
        this.horarios = horarios;
        this.servicos = servicos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<HorarioFuncionamento> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioFuncionamento> horarios) {
        this.horarios = horarios;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
}
