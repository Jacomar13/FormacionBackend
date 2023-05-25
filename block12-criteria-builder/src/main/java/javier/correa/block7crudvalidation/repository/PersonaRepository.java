package javier.correa.block7crudvalidation.repository;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;


public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByUsuario(String user);

    List<PersonaOutputDto> getCustomQuery(HashMap<String, Object> conditions);

}
