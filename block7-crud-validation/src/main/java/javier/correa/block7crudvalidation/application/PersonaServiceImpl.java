package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.exception.UnprocesableException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws UnprocesableException {
        if (persona.getUsuario()== null) {throw new UnprocesableException("El usuario no puede estar vacío", 422);}
        else if (persona.getUsuario().length() < 6 || persona.getUsuario().length() > 10){throw new UnprocesableException("La longitud de usuario no pueder ser inferior a 6 ni superior a 10 caracteres", 422);}
        else if (Objects.isNull(persona.getPassword()) || persona.getPassword().isBlank()) {throw new UnprocesableException("La contraseña no puede estar vacía", 422);}
        else if (persona.getName() == null) {throw new UnprocesableException("El nombre no puede estar vacío", 422);}
        else if (persona.getCompany_email() == null) {throw new UnprocesableException("El correo profesional no puede estar vacío", 422);}
        else if (persona.getPersonal_email() == null) {throw new UnprocesableException("El correo personal no puede estar vacío", 422);}
        else if (persona.getCity() == null) {throw new UnprocesableException("La ciudad no puede estar vacía", 422);}
        else if (persona.isActive() == false) {throw new UnprocesableException("El usuario no puede estar NO ACTIVADO", 422);}
        else if (persona.getCreated_date() == null) {throw new UnprocesableException("La fecha de creación no puede estar vacía", 422);}
        return personaRepository.save(new Persona(persona)).personaToOutputDto();
    }

    @Override
    public PersonaOutputDto getPersonaById(Integer id) throws EntityNotFoundException {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        Persona persona = personaOptional.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con id " + id, 404));
        PersonaOutputDto personaOutputDto = persona.personaToOutputDto();
        return personaOutputDto;
    }


    @Override
    public List<PersonaOutputDto> getPersonabyUsuario(String user) throws EntityNotFoundException {
        List<Persona> listPersona = personaRepository.findByUsuario(user);

        if (listPersona.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado ninguna persona con el nombre de usuario: " + user, 404);

        List<PersonaOutputDto> personaOutputDtoList = listPersona.stream()
                .map(Persona::personaToOutputDto)
                .toList();
        return personaOutputDtoList;
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) throws EntityNotFoundException{
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        if (personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToOutputDto)
                .toList().isEmpty())
                    throw new EntityNotFoundException("No hay ninguna persona registrada en la base de datos actualmente",404);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToOutputDto)
                .toList();
    }
}
