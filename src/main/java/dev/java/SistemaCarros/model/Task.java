package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate dataServico;
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "mechanical_id", nullable = false)
    private Mechanical mechanical;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Task() {
    }
    public Task(Long id, String descricao, LocalDate dataServico, BigDecimal valorPago, Mechanical mechanical,
                Car car) {
        this.id = id;
        this.descricao = descricao;
        this.dataServico = dataServico;
        this.valorPago = valorPago;
        this.mechanical = mechanical;
        this.car = car;
    }
}
