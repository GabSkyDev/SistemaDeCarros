package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "servico_realizado")
public class ServicoRealizado {

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
    @JoinColumn(name = "carro_id", nullable = false)
    private Carro carro;


}
