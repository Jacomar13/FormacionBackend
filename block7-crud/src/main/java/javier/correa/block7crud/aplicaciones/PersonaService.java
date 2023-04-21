package javier.correa.block7crud.aplicaciones;

import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(int id);

    void deletePersonaById(int id);

    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);

    PersonaOutputDto updatePersona (int id, PersonaInputDto persona);
}
