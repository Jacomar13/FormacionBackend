package javier.correa.block7crud.aplicaciones;

import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(int id);

    List<PersonaOutputDto> getPersonaByNombre(String name);

    void deletePersonaById(int id);

    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);

    PersonaOutputDto updatePersona (int id, PersonaInputDto persona);
}
