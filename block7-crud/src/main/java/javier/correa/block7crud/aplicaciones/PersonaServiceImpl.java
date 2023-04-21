package javier.correa.block7crud.aplicaciones;

import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import javier.correa.block7crud.dominio.Persona;
import javier.correa.block7crud.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public void deletePersonaById(int id) {
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        //PageRequest es una clase de Spring Data que permite construir objetos de paginación
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToOutputDto)
                .toList();
    }

    @Override
    public PersonaOutputDto updatePersona(int id, PersonaInputDto persona) {
        personaRepository.findById(id).orElseThrow();
        return personaRepository.save(new Persona(persona)).personaToOutputDto();
    }

}
