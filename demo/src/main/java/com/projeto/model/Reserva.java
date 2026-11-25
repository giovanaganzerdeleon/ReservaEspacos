package com.projeto.model;


import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

       @Column(name = "data", nullable = false)
    private LocalDate data;

        @Column(name = "horario", nullable = false)
    private LocalTime horario;

       @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

   
    @Column(name = "local", nullable = false, length = 100)
    private String local;

    @Column(name = "disponibilidade", nullable = false)
    private boolean disponibilidade = true;

    // =========================================================
    // CONSTRUTORES
    // =========================================================

    public Reserva() {
    }

    public Reserva(LocalDate data, LocalTime horario, String tipo, String local, boolean disponibilidade) {
        this.data = data;
        this.horario = horario;
        this.tipo = tipo;
        this.local = local;
        this.disponibilidade = disponibilidade;
    }

    // =========================================================
    // GETTERS E SETTERS
    // =========================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
