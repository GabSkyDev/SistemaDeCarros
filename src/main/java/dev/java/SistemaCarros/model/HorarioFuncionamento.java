package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.DayOfWeek;


@Entity
@Table(name = "horario_funcionamento")
public class HorarioFuncionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private DayOfWeek diaSemana;

    @Column(name = "abertura", nullable = false)
    private LocalTime abertura;

    @Column(name = "fechamento", nullable = false)
    private LocalTime fechamento;

    @OneToOne
    @JoinColumn(name = "mecanica_id", nullable = false)
    private Mecanica mecanica;

    public HorarioFuncionamento() {}

    public HorarioFuncionamento(DayOfWeek diaSemana, LocalTime abertura, LocalTime fechamento, Mecanica mecanica) {
        this.diaSemana = diaSemana;
        this.abertura = abertura;
        this.fechamento = fechamento;
        this.mecanica = mecanica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalTime abertura) {
        this.abertura = abertura;
    }

    public LocalTime getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalTime fechamento) {
        this.fechamento = fechamento;
    }

    public Mecanica getMecanica() {
        return mecanica;
    }

    public void setMecanica(Mecanica mecanica) {
        this.mecanica = mecanica;
    }
}
