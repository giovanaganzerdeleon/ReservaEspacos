package com.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipamentos")
public class Equipamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    
    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    // =========================================================
    // CONSTRUTORES
    // =========================================================

    public Equipamentos() {
    }

    public Equipamentos(String nome, String modelo, String tipo, Integer quantidade) {
        this.nome = nome;
        this.modelo = modelo;
        this.tipo = tipo;
        this.quantidade = quantidade;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

