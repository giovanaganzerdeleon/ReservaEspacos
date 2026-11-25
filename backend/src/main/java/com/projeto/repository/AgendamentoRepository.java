package com.projeto.repository;

import com.projeto.model.Agendamento;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @EntityGraph(attributePaths = "sala")
    List<Agendamento> findByUsuario_IdOrderByDataAscHoraInicioAsc(Long usuarioId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select reserva from Agendamento reserva where reserva.id = :id")
    Optional<Agendamento> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select case when count(reserva) > 0 then true else false end
            from Agendamento reserva
            where reserva.sala.id = :salaId
              and reserva.data = :data
              and reserva.cancelada = false
              and reserva.horaInicio < :horaFim
              and reserva.horaFim > :horaInicio
              and (:ignorarId is null or reserva.id <> :ignorarId)
            """)
    boolean existeConflito(
            @Param("salaId") Long salaId,
            @Param("data") LocalDate data,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFim") LocalTime horaFim,
            @Param("ignorarId") Long ignorarId
    );
}
