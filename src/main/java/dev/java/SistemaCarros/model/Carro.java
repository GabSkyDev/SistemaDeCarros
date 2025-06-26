package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private Integer anoFabricacao;
    private String cor;
    private String tipoCombustivel;
    private String transmissao;

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL)
    private List<Servico> servicos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    public Carro() {

    }
    public Carro(Long id, String placa, String modelo, String marca, Integer anoFabricacao, String cor, String tipoCombustivel, String transmissao) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
        this.tipoCombustivel = tipoCombustivel;
        this.transmissao = transmissao;
    }
}
