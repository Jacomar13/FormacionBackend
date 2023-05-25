package javier.correa.block7crudvalidation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonaRepositoryImpl{
    @PersistenceContext
    private EntityManager entityManager;

    public List<PersonaOutputDto> getCustomQuery(
            HashMap<String, Object> conditions, String type) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "name":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    /*if (type.equals("asc")){
                        query.orderBy(cb.asc(root.get("name")));
                    }
                    if (type.equals("desc")){
                       query.orderBy(cb.desc(root.get("name")));
                    }*/
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    /*if (type.equals("asc")){
                        query.orderBy(cb.asc(root.get("surname")));
                    }
                    if (type.equals("desc")){
                        query.orderBy(cb.desc(root.get("surname")));
                    }*/
                    break;
                case "created_date":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    /*if (type.equals("asc")){
                        query.orderBy(cb.asc(root.get("created_date")));
                    }
                    if (type.equals("desc")){
                        query.orderBy(cb.desc(root.get("created_date")));
                    }*/
                    break;
                case "usuario":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    /*if (type.equals("asc")){
                        query.orderBy(cb.asc(root.get("usuario")));
                    }
                    if (type.equals("desc")){
                        query.orderBy(cb.desc(root.get("usuario")));
                    }*/
                    break;
            }
            if (type.equals("desc")){
                query.orderBy(cb.desc(root.get(field)));
            }
        });


        query.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager
                .createQuery(query)
                .getResultList()
                .stream()
                .map(Persona::personaToOutputDto)
                .toList();
    }
}

