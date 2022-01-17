package com.alkemy.preaceleracion.repositories;

import com.alkemy.preaceleracion.entities.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity,Long> {
}
