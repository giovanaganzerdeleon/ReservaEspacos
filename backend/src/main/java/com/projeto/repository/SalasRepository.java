
package com.projeto.repository;

import com.projeto.model.Salas;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SalasRepository extends JpaRepository<Salas, Long> {

    List<Salas> findByDisponibilidadeTrue();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select sala from Salas sala where sala.id = :id")
    Optional<Salas> findByIdForUpdate(@Param("id") Long id);
}
