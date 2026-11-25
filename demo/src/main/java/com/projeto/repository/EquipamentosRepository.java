
package com.projeto.repository;

import com.projeto.model.Equipamentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipamentosRepository
        extends JpaRepository<Equipamentos, Long> {

    // Buscar pelo nome
    List<Equipamentos> findByNomeContainingIgnoreCase(String nome);

    // Buscar pelo tipo
    List<Equipamentos> findByTipoContainingIgnoreCase(String tipo);

    // Buscar pelo modelo
    List<Equipamentos> findByModeloContainingIgnoreCase(String modelo);
}
