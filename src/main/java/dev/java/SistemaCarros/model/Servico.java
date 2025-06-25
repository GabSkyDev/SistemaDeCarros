package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "servico_realizado")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate dataServico;
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "mecanica_id", nullable = false)
    private Mecanica mecanica;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;


    public Servico() {
    }

    
    public Servico(Long id, String descricao, LocalDate dataServico, BigDecimal valorPago, Mecanica mecanica,
                   Carro carro) {
        this.id = id;
        this.descricao = descricao;
        this.dataServico = dataServico;
        this.valorPago = valorPago;
        this.mecanica = mecanica;
        this.carro = carro;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public LocalDate getDataServico() {
        return dataServico;
    }


    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }


    public BigDecimal getValorPago() {
        return valorPago;
    }


    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }


    public Mecanica getMecanica() {
        return mecanica;
    }


    public void setMecanica(Mecanica mecanica) {
        this.mecanica = mecanica;
    }


    public Carro getCarro() {
        return carro;
    }


    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    

}
