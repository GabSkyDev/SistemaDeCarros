package dev.java.SistemaCarros.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TaskResponseDTO {
    private String descricao;
    private LocalDate dataServico;
    private BigDecimal valorPago;
    private Long mecanicaId;
    private String mecanicaNome;
    private Long carroId;
    private String placaCarro;
    private String modeloCarro;

    public TaskResponseDTO(){

    }

    public TaskResponseDTO(String descricao, LocalDate dataServico, BigDecimal valorPago, Long mecanicaId, String mecanicaNome, Long carroId, String placaCarro, String modeloCarro) {
        this.descricao = descricao;
        this.dataServico = dataServico;
        this.valorPago = valorPago;
        this.mecanicaId = mecanicaId;
        this.mecanicaNome = mecanicaNome;
        this.carroId = carroId;
        this.placaCarro = placaCarro;
        this.modeloCarro = modeloCarro;
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

    public String getMecanicaNome() {
        return mecanicaNome;
    }

    public void setMecanicaNome(String mecanicaNome) {
        this.mecanicaNome = mecanicaNome;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }
}
