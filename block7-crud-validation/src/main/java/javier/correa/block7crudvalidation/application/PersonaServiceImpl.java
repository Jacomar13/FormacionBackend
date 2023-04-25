package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        return personaRepository.save(new Persona(persona)).personaToOutputDto();
    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow().personaToOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getPersonabyUsuario(String user) {
        List<Persona> listPersona = personaRepository.findByUsuario(user);
        List<PersonaOutputDto> personaOutputDtoList = listPersona.stream()
                .map(Persona::personaToOutputDto)
                .toList();
        return personaOutputDtoList;
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pagenumber, int pageSize) {
        return null;
    }
}
