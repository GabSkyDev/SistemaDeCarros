package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "car")
public class Car {
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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Task> servicos;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    public Car() {

    }
    public Car(Long id, String placa, String modelo, String marca, Integer anoFabricacao, String cor, String tipoCombustivel, String transmissao) {
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
