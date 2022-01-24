package com.alkemy.preaceleracion.service.specification;


import com.alkemy.preaceleracion.dto.PeliculaFilterDto;
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
public class PeliculaEspecificacion {

    public Specification<Personaje> getByFilters(PeliculaFilterDto filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getNombre())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + filtersDTO.getNombre().toLowerCase() + "%"));
            }
            if (StringUtils.hasLength(filtersDTO.getIdGenero().toString())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("idGenero")),
                        "%" + filtersDTO.getIdGenero().toString().toLowerCase() + "%"));
            }


            // Remove duplucates
            query.distinct(true);

            String orderByField = "nombre";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
