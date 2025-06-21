package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
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
    private List<ServicoRealizado> servicos;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }
}
