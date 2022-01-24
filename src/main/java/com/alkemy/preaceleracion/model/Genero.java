package com.alkemy.preaceleracion.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Setter
@Getter
@Table(name = "genero")
@SQLDelete(sql = "UPDATE genero SET deleted = true WHERE id=? ")
@Where(clause = "deleted=false")
@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String imagen;

    private Boolean deleted = Boolean.FALSE;

}
