
package com.projeto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.model.Reserva;
import com.projeto.model.StatusReserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Buscar reservas de um usuário
    List<Reserva> findByUsuario_Id(Long usuarioId);

    // Buscar reservas de uma data
    List<Reserva> findByData(LocalDate data);

    // Buscar reservas de uma sala
    List<Reserva> findBySala_Id(Long salaId);

    // Buscar reservas por status
    List<Reserva> findByStatus(StatusReserva status);

    // Buscar reservas de um usuário em uma data
    List<Reserva> findByUsuario_IdAndData(Long usuarioId, LocalDate data);
}
