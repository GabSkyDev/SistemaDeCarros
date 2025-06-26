package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(mappedBy = "mecanica", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Servico> servicos = new ArrayList<>();

    public Mecanica() {}

    public Mecanica(Long id, String cnpj, String nome, String endereco, String email, String telefone, List<String> especialidades, List<HorarioFuncionamento> horarios, List<Servico> servicos) {
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
}

