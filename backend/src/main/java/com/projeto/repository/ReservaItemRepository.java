package com.projeto.repository;

import com.projeto.model.ReservaItem;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReservaItemRepository extends JpaRepository<ReservaItem, Long> {

    @EntityGraph(attributePaths = "item")
    List<ReservaItem> findByUsuario_IdOrderByDataAscHoraInicioAsc(Long usuarioId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select reserva from ReservaItem reserva where reserva.id = :id")
    Optional<ReservaItem> findByIdForUpdate(@Param("id") Long id);

    @Query("""
            select sum(reserva.quantidade)
            from ReservaItem reserva
            where reserva.item.id = :itemId
              and reserva.data = :data
              and reserva.cancelada = false
              and reserva.horaInicio < :horaFim
              and reserva.horaFim > :horaInicio
              and (:ignorarId is null or reserva.id <> :ignorarId)
            """)
    Long somarQuantidadeReservada(
            @Param("itemId") Long itemId,
            @Param("data") LocalDate data,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFim") LocalTime horaFim,
            @Param("ignorarId") Long ignorarId
    );
}
