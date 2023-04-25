package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(int id);

    List<PersonaOutputDto> getPersonabyUsuario(String name);

    Iterable<PersonaOutputDto> getAllPersonas(int pagenumber, int pageSize);

}
