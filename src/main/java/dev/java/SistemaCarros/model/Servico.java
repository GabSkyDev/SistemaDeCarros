package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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
}
