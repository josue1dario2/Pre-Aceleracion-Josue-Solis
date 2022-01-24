package com.alkemy.preaceleracion.service.specification;

import com.alkemy.preaceleracion.dto.PersonajeFilterDto;
import com.alkemy.preaceleracion.model.Pelicula;
import com.alkemy.preaceleracion.model.Personaje;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeEspecificacion {

    public Specification<Personaje> getByFilters(PersonajeFilterDto filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getNombre())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + filtersDTO.getNombre().toLowerCase() + "%"));
            }
            if (StringUtils.hasLength(filtersDTO.getEdad())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("edad")),
                        "%" + filtersDTO.getEdad().toLowerCase() + "%"));
            }


            if (!CollectionUtils.isEmpty(filtersDTO.getPeliculas())) {
                Join<Pelicula, Personaje> join = root.join("peliculas", JoinType.INNER);
                Expression<String> citiesId = join.get("id");
                predicates.add(citiesId.in(filtersDTO.getPeliculas()));
            }

            // Remove duplucates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
