package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.exception.UnprocessableException;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona) throws Exception;

    Object getPersonaById(Integer id, String personType);

    List<PersonaOutputDto> getPersonabyUsuario(String name);

    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);


    void deletePersonaById(int id) throws UnprocessableException;

    PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto);

}
