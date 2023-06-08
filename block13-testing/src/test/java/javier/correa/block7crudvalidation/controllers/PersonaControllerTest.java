package javier.correa.block7crudvalidation.controllers;

import javier.correa.block7crudvalidation.application.PersonaServiceImpl;
import javier.correa.block7crudvalidation.application.ProfesorClient;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonaControllerTest {

    PersonaInputDto personaInputDto;
    PersonaOutputDto personaOutputDto;

    int id = 1;

    @Mock
    private PersonaServiceImpl personaService;
    private PersonaController personaController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personaController = new PersonaController();
        personaController.personaService = personaService;
        this.personaInputDto = new PersonaInputDto();
        this.personaOutputDto = new PersonaOutputDto();
    }
    @Test
    void addPersona() throws Exception {


        Mockito.when(personaService.addPersona(personaInputDto)).thenReturn(personaOutputDto);

        ResponseEntity<PersonaOutputDto>  respuesta = personaController.addPersona(personaInputDto);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        Mockito.verify(personaService).addPersona(personaInputDto);

    }

    @Test
    void showPersonaById() {

        String outputType = "full";

        Mockito.when(personaService.getPersonaById(id, outputType)).thenReturn(personaOutputDto);
        ResponseEntity respuesta = personaController.showPersonaById(id, outputType);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(personaOutputDto, respuesta.getBody());
        Mockito.verify(personaService).getPersonaById(id, outputType);
    }

    @Test
    void showPersonaByUser() {
        String user = "Javier03";
        List<PersonaOutputDto> listaPersonas = new ArrayList<>();
        listaPersonas.add(personaOutputDto);
        Mockito.when(personaService.getPersonabyUsuario(user)).thenReturn(listaPersonas);

        ResponseEntity respuesta = personaController.showPersonaByUser(user);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(listaPersonas, respuesta.getBody());
        Mockito.verify(personaService).getPersonabyUsuario(user);
    }

    @Test
    void showAllPersonas() {
        int pageNumber = 0;
        int pageSize = 25;
        List<PersonaOutputDto> listaFicticia = new ArrayList<>();
        Mockito.when(personaService.getAllPersonas(pageNumber,pageSize)).thenReturn(listaFicticia);

        List<PersonaOutputDto> listaResultado = (List<PersonaOutputDto>) personaController.showAllPersonas(pageNumber, pageSize);
        assertEquals(listaFicticia, listaResultado);
    }

    @Test
    void deletePersona() {

        ResponseEntity<String> respuesta = personaController.deletePersona(id);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("Persona con el id: " + id + " ha sido eliminada", respuesta.getBody());

        Mockito.verify(personaService).deletePersonaById(id);
    }

    @Test
    void updatePersona() {

        Mockito.when(personaService.updatePersona(id, personaInputDto)).thenReturn(personaOutputDto);

        ResponseEntity<PersonaOutputDto> respuesta = personaController.updatePersona(id, personaInputDto);
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Mockito.verify(personaService).updatePersona(id, personaInputDto);
    }
}