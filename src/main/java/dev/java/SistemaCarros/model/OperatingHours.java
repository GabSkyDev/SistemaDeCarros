package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.DayOfWeek;


@Embeddable
public class OperatingHours {
    @Enumerated(EnumType.STRING)
    private DayOfWeek diaSemana;
    private LocalTime abertura;
    private LocalTime fechamento;

    public OperatingHours() {}

    public OperatingHours(DayOfWeek diaSemana, LocalTime abertura, LocalTime fechamento) {
        this.diaSemana = diaSemana;
        this.abertura = abertura;
        this.fechamento = fechamento;
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
}
