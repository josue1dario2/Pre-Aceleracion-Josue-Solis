package com.alkemy.preaceleracion.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "pelicula")
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imagen;
    @Column
    private String titulo;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDeCreacion;
    @Column
    private Integer calificacion;

    private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id",insertable = false,updatable = false)
    private GeneroEntity genero;

    @Column(name = "genero_id",nullable = false)
    private Long generoId;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "pelicula_personaje",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private List<PersonajeEntity> personajes = new ArrayList<>();
}
