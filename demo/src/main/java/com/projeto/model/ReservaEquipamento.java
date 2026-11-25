
package com.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reserva_equipamentos")
public class ReservaEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    // Relacionamento com Reserva
    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;
    

    // Relacionamento com Equipamentos
    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamentos equipamento;

    public ReservaEquipamento() {
    }

    public ReservaEquipamento(
            Integer quantidade,
            Reserva reserva,
            Equipamentos equipamento) {

        this.quantidade = quantidade;
        this.reserva = reserva;
        this.equipamento = equipamento;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Equipamentos getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamentos equipamento) {
        this.equipamento = equipamento;
    }
}
