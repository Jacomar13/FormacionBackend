package javier.correa.block13mongodb.application;

import javier.correa.block13mongodb.controller.dto.PersonaInputDto;
import javier.correa.block13mongodb.controller.dto.PersonaOutputDto;
import javier.correa.block13mongodb.domain.Persona;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PersonaService {
    PersonaOutputDto savePerson(PersonaInputDto persona);
    List<Persona> getAllPerson();
    List<Persona> getAllPersonPaginated(
            int pageNumber, int pageSize);
    Persona findOneByName(String name);
    List<Persona> findByName(String name);

    PersonaOutputDto updateOnePerson(String personId, PersonaInputDto persona);
    void deletePerson(PersonaInputDto persona);
}
