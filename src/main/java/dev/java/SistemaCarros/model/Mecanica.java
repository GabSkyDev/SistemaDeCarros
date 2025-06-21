package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "mecanica")
public class Mecanica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String nome;
    private String endereco;
    private String email;
    private String telefone;

    @ElementCollection
    @CollectionTable(name = "mecanica_especialidades", joinColumns = @JoinColumn(name = "mecanica_id"))
    private List<String> especialidades;

    @ElementCollection
    @CollectionTable(name = "mecanica_horarios", joinColumns = @JoinColumn(name = "mecanica_id"))
    private List<HorarioFuncionamento> horarios;

    @OneToMany(mappedBy = "mecanica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoRealizado> servicos;

    public Mecanica() {}

    public Mecanica(Long id, String cnpj, String nome, String endereco, String email, String telefone, List<String> especialidades, List<HorarioFuncionamento> horarios, List<ServicoRealizado> servicos) {
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

    public List<ServicoRealizado> getServicos() {
        return servicos;
    }

    public void setServicos(List<ServicoRealizado> servicos) {
        this.servicos = servicos;
    }
}

