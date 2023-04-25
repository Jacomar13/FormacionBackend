package javier.correa.block7crud.aplicaciones;

import javier.correa.block7crud.controladores.dto.PersonaInputDto;
import javier.correa.block7crud.controladores.dto.PersonaOutputDto;
import javier.correa.block7crud.dominio.Persona;
import javier.correa.block7crud.repositorio.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<PersonaOutputDto> getPersonaByNombre(String nombre) {
        List<Persona> listPersona = personaRepository.findByNombre(nombre);
        List<PersonaOutputDto> listPersonaOutput = listPersona.stream()
                .map(Persona::personaToOutputDto)
                .toList();
        return listPersonaOutput;
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
    public Persona updatePersona(int id, PersonaInputDto personaInputDto) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
        if (personaInputDto.getNombre() != null) {
            persona.setNombre(personaInputDto.getNombre());
        }
        if (personaInputDto.getEdad() != null) {
            persona.setEdad(personaInputDto.getEdad());
        }
        if (personaInputDto.getPoblacion() != null) {
            persona.setPoblacion(personaInputDto.getPoblacion());
        }

        Persona personaGuardada = personaRepository.save(persona);
        System.out.println(personaGuardada);

        return personaGuardada;
    }

}
