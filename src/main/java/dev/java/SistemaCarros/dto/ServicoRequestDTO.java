package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.Carro;
import dev.java.SistemaCarros.model.Mecanica;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServicoRequestDTO {
    private Long id;
    private String descricao;
    private LocalDate dataServico;
    private BigDecimal valorPago;
    private Long mecanicaId;
    private Long carroId;

    public ServicoRequestDTO(){

    }

    public ServicoRequestDTO(Long id, String descricao, LocalDate dataServico, BigDecimal valorPago, Long mecanicaId, Long carroId) {
        this.id = id;
        this.descricao = descricao;
        this.dataServico = dataServico;
        this.valorPago = valorPago;
        this.mecanicaId = mecanicaId;
        this.carroId = carroId;
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

    public Long getMecanicaId() {
        return mecanicaId;
    }

    public void setMecanicaId(Long mecanicaId) {
        this.mecanicaId = mecanicaId;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }
}
