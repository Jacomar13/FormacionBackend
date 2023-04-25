package javier.correa.block7crud.aplicaciones;

import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import javier.correa.block7crud.dominio.Persona;

import java.util.List;

public interface PersonaService {

    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto getPersonaById(int id);

    List<PersonaOutputDto> getPersonaByNombre(String name);

    void deletePersonaById(int id);

    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);

    Persona updatePersona (int id, PersonaInputDto personaInputDto);
}
