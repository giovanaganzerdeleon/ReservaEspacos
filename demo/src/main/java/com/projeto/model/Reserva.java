
package com.projeto.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "horario_inicio", nullable = false)
    private LocalTime horarioInicio;

    @Column(name = "horario_fim", nullable = false)
    private LocalTime horarioFim;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusReserva status = StatusReserva.CONFIRMADA;

    // Relacionamento com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relacionamento com Salas
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = true)
    private Salas sala;

    // Relacionamento com ReservaEquipamento
    @OneToMany(
        mappedBy = "reserva",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ReservaEquipamento> equipamentos = new ArrayList<>();
    public void adicionarEquipamento(ReservaEquipamento item) {
    equipamentos.add(item);
    item.setReserva(this);
}

    public Reserva() {
    }

    public Reserva(
            LocalDate data,
            LocalTime horarioInicio,
            LocalTime horarioFim,
            String tipo,
            Usuario usuario,
            Salas sala) {

        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.tipo = tipo;
        this.usuario = usuario;
        this.sala = sala;
        this.status = StatusReserva.CONFIRMADA;
    }

    // GETTERS E SETTERS

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

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Salas getSala() {
        return sala;
    }

    public void setSala(Salas sala) {
        this.sala = sala;
    }

    public List<ReservaEquipamento> getEquipamentos() {
        return equipamentos;
    }

   public void setEquipamentos(List<ReservaEquipamento> equipamentos) {
    this.equipamentos.clear();

    if (equipamentos != null) {
        for (ReservaEquipamento item : equipamentos) {
            adicionarEquipamento(item);
        }
    }
}
}
