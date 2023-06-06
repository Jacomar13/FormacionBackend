package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;

import javier.correa.block7crudvalidation.repository.ProfesorRepository;
import javier.correa.block7crudvalidation.repository.StudentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hamcrest.Matchers.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

class PersonaServiceTest {
    Persona persona;
    Persona persona2;
    Integer contador = 1;
    PersonaInputDto personaIntroducida;
    @Mock
    PersonaRepository personaRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    ProfesorRepository profesorRepository;
    @InjectMocks
    private PersonaServiceImpl personaService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona = new Persona(personaIntroducida);
        contador = contador + 1;
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona2 = new Persona(personaIntroducida);
        personaRepository.save(persona);
        personaRepository.save(persona2);

    }

    @Test
    @DisplayName("Añadir una persona")
    void addPersona_validInput() {

        //Cuando hacemos un save en addPersona, se le devuelve una Persona, haciendo así que no nos de un error
        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona);

        //Cogemos el PersonaOutputDto que nos devuelve el método
        PersonaOutputDto personaDevuelta = personaService.addPersona(personaIntroducida);

        //Verificamos que el save de personaRepository, se haya utilizado 3 veces, escogemos 3 porque previamente en el setUp ya hemos metido dos, pero con este test metemos un 3º
        Mockito.verify(personaRepository, times(3)).save(Mockito.any(Persona.class));

        //Comprobamos que personaDevuelta no sea nula
        assertNotNull(personaDevuelta);

    }


    @Test
    void getPersonaById() {
        Optional<Persona> optionalPersona = Optional.of(persona);

        // Caso en el que es persona pero nada más
        Mockito.when(personaRepository.findById(1)).thenReturn(optionalPersona);

        PersonaOutputDto personaDevuelta = (PersonaOutputDto) personaService.getPersonaById(1, "persona");

        Mockito.verify(personaRepository, Mockito.times(1)).findById(1);
        assertNotNull(personaDevuelta);

        //Caso en el que persona es estudiante y el tipo de persona es correcto

        Mockito.when(studentRepository.findByIdPersona(2)).thenReturn(null);
        Mockito.when(profesorRepository.findByIdPersona(2)).thenReturn(null);

    }

    @Test
    public void testGetPersonabyUsuario() throws EntityNotFoundException {
        String user = "Javier03";

        // Crear una lista de personas ficticia para simular el resultado de personaRepository.findByUsuario(user)
        List<Persona> listaFicticia = new ArrayList<>();
        listaFicticia.add(persona);
        listaFicticia.add(persona2);

        // Simulamos que cuando pasamos por usuario nos devuelva una lista
        Mockito.when(personaRepository.findByUsuario(user)).thenReturn(listaFicticia);

        // Llamar al método que se está probando
        List<PersonaOutputDto> resultado = personaService.getPersonabyUsuario(user);

        //Comprobar que tiene algo resultado
        System.out.println(resultado);

        // Verificamos que hayan obtenido la misma cantidad de personas
        assertEquals(listaFicticia.size(), resultado.size());


    }

    @Test
    void getAllPersonas() {

        // Crear una lista de personas ficticia
        List<Persona> listaFicticia = new ArrayList<>();
        listaFicticia.add(persona);
        listaFicticia.add(persona2);
        //Con esto hacemos las páginas según la lista
        Page<Persona> personaPage = new PageImpl<>(listaFicticia);

        Mockito.when(personaRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(personaPage);

        // En el caso de la búsqueda
        Iterable<PersonaOutputDto> resultado = personaService.getAllPersonas(0, 25);
        List<PersonaOutputDto> listaResultado = StreamSupport.stream(resultado.spliterator(), false)
                .collect(Collectors.toList());
        assertNotNull(listaResultado);
        assertEquals(listaFicticia.size(), listaResultado.size());

    }


    @Test
    void deletePersonaById() {
        Optional<Persona> optionalPersona = Optional.of(persona);
        Mockito.when(studentRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(profesorRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(personaRepository.findById(1)).thenReturn(optionalPersona);

        // Act
        personaService.deletePersonaById(1);

        // Assert
        Mockito.verify(personaRepository, Mockito.times(1)).findById(1);
        Mockito.verify(personaRepository, Mockito.times(1)).deleteById(1);

    }

    @Test
    void updatePersona() {
        Integer id = 1;
        Persona persona3 = new Persona(new PersonaInputDto(null, "Javier03", "contraseña", "Javier", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date()));
        persona3.setId_persona(id);
        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona3);

        //Cogemos el PersonaOutputDto que nos devuelve el método
        PersonaOutputDto personaDevuelta = personaService.updatePersona(id, personaIntroducida);


        //Verificamos que el save de personaRepository, se haya utilizado 3 veces, escogemos 3 porque previamente en el setUp ya hemos metido dos, pero con este test metemos un 3º
        Mockito.verify(personaRepository, times(3)).save(Mockito.any(Persona.class));

        //Comprobamos que personaDevuelta no sea nula
        assertNotNull(personaDevuelta);


    }


}