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
            HashMap<String, Object> conditions, String type, String nameOrUser, int pageNumber,int pageSize) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "name":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    break;
                case "created_date":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    break;
                case "usuario":
                    predicates.add(cb.like(root.get(field),
                            "%" + (String) value + "%"));
                    break;
            }
            if (type.equals("desc")) {
                if (nameOrUser.equals("name") || nameOrUser.equals("usuario")){

                    query.orderBy(cb.desc((root.get(nameOrUser))));
                    query.orderBy(cb.desc((root.get(field))));
                } else {
                    query.orderBy(cb.desc((root.get(field))));
                }
            } else {
                if (nameOrUser.equals("name") || nameOrUser.equals("usuario")){
                    query.orderBy(cb.asc((root.get(nameOrUser))));
                    query.orderBy(cb.asc((root.get(field))));
                } else {
                    query.orderBy(cb.asc((root.get(field))));
                }
            }
        });


        query.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));
        int firstResult = (pageNumber - 1) * pageSize;

        return entityManager
                .createQuery(query)
                .setFirstResult(firstResult)
                .setMaxResults(pageSize)
                .getResultList()
                .stream()
                .map(Persona::personaToOutputDto)
                .toList();
    }
}

