package com.alkemy.preaceleracion.repository;

import com.alkemy.preaceleracion.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {

    @Query(value = "SELECT * FROM pelicula p WHERE p.generoId = ?1 ",nativeQuery = true)
    List<Pelicula> findByIdPelicula(Long generoId);
}
