package com.projeto.repository;

import com.projeto.model.ItemCampus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemCampusRepository extends JpaRepository<ItemCampus, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select item from ItemCampus item where item.id = :id")
    Optional<ItemCampus> findByIdForUpdate(@Param("id") Long id);
}
