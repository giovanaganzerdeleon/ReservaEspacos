
package com.projeto.repository;

import com.projeto.model.Salas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalasRepository extends JpaRepository<Salas, Long> {

    List<Salas> findByDisponibilidadeTrue();

}
