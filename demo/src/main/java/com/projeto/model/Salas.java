package com.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salas")
public class Salas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "capacidade", nullable = false)
    private Integer capacidade;


    @Column(name = "disponibilidade", nullable = false)
    private boolean disponibilidade = true;

    // =========================================================
    // CONSTRUTORES
    // =========================================================

    public Salas() {
    }

    public Salas(String nome, Integer capacidade, boolean disponibilidade) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.disponibilidade =disponibilidade;
    }

    // =========================================================
    // GETTERS E SETTERS
    // =========================================================
    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
}


