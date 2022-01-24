package com.alkemy.preaceleracion.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Table(name = "pelicula")
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")
@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaDeCreacion;
    private Integer calificacion;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id",insertable = false,updatable = false)
    private Genero genero;

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
    private List<Personaje> personajes = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;


    public void addPersonaje(Personaje personaje){

        this.personajes.add(personaje);
    }
    public void removePersonaje(Personaje personaje){

        this.personajes.remove(personaje);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Pelicula other = (Pelicula) obj;
        return other.id == this.id;
    }
}
