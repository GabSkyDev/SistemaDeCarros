package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mechanical")
public class Mechanical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String nome;
    private String endereco;
    private String email;
    private String telefone;

    @ElementCollection
    @CollectionTable(name = "mechanical_especialidades", joinColumns = @JoinColumn(name = "mechanical_id"))
    private List<String> especialidades;

    @ElementCollection
    @CollectionTable(name = "mechanical_horarios", joinColumns = @JoinColumn(name = "mechanical_id"))
    private List<OperatingHours> horarios;

    @OneToMany(mappedBy = "mechanical", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> servicos = new ArrayList<>();

    public Mechanical() {}

    public Mechanical(Long id, String cnpj, String nome, String endereco, String email, String telefone, List<String> especialidades, List<OperatingHours> horarios, List<Task> servicos) {
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

