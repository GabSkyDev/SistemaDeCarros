package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import dev.java.SistemaCarros.model.Usuario;
import java.util.List;

@Entity
@Table(name = "carro")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carro;
    private String placa;
    private String modelo;
    private String marca;
    private Integer ano_fabricacao;
    private String cor;
    private String tipo_combustivel;
    private String transmissao;

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ServicoRealizado> servicos;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    public Carro() {

    }

    public Carro(Long id_carro, String placa, String modelo, String marca, Integer ano_fabricacao, String cor, String tipo_combustivel, String transmissao) {
        this.id_carro = id_carro;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano_fabricacao = ano_fabricacao;
        this.cor = cor;
        this.tipo_combustivel = tipo_combustivel;
        this.transmissao = transmissao;
    }

    public Long getId_carro() {
        return id_carro;
    }

    public void setId_carro(Long id_carro) {
        this.id_carro = id_carro;
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

    public Integer getAno_fabricacao() {
        return ano_fabricacao;
    }

    public void setAno_fabricacao(Integer ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo_combustivel() {
        return tipo_combustivel;
    }

    public void setTipo_combustivel(String tipo_combustivel) {
        this.tipo_combustivel = tipo_combustivel;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }
}
