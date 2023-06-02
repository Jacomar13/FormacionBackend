package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.hamcrest.Matchers.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

class PersonaServiceTest {
    Persona persona;

    @Mock
    PersonaRepository personaRepository;
    @InjectMocks
    private PersonaServiceImpl personaService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        this.persona =new Persona( new PersonaInputDto(1, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date()));
        personaRepository.save(persona);
    }

    @Test
    @DisplayName("Añadir una persona")
    void addPersona_validInput() {


        when(personaRepository.findById(persona.getId_persona())).thenReturn(Optional.of(persona));

        /*try {
            personaService.addPersona(personaInput1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        Optional<Persona> personaEncontrada = personaRepository.findById(persona.getId_persona());

        assertNotNull(personaEncontrada.get().getId_persona());

    }


    @Test
    void getPersonaById() {
    }

    @Test
    void getPersonabyUsuario() {
    }

    @Test
    void getAllPersonas() {
    }

    @Test
    void deletePersonaById() {
    }

    @Test
    void updatePersona() {
    }


}