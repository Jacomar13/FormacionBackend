package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorSimpleOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import javier.correa.block7crudvalidation.repository.ProfesorRepository;
import javier.correa.block7crudvalidation.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class ProfesorServiceTest {

    Persona persona1;
    Persona persona2;

    Profesor profesor1;
    Profesor profesor2;
    Student student;
    Student student2;
    Integer contador = 1;
    PersonaInputDto personaIntroducida;
    ProfesorInputDto profesorIntroducido1;
    ProfesorInputDto profesorIntroducido2;
    StudentInputDto estudianteIntroducido1;
    StudentInputDto estudianteIntroducido2;
    ModelMapper modelMapper = new ModelMapper();

    @Mock
    PersonaRepository personaRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    ProfesorRepository profesorRepository;
    @InjectMocks
    private ProfesorServiceImpl profesorService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        // Creamos dos profesores con sus inputDto, dos personas y dos estudiantes
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona1 = new Persona(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date(), null, null);
        this.profesorIntroducido1 = new ProfesorInputDto(contador, contador,  "Muy bueno", "ASIR");
        this.profesor1 = new Profesor(contador, persona1, "Muy bueno", "ASIR", null);
        this.estudianteIntroducido1 = new StudentInputDto(contador, contador, 40, "Muy bueno", "ASIR");
        this.student = new Student(contador, 40, "Muy bueno", "ASIR", null, null, null);
        persona1.setId_persona(contador);
        // Establecemos a cada profesor una persona
        profesor1.setPersona(persona1);
        contador = contador + 1;
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona2 = new Persona(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date(), null, null);
        this.profesorIntroducido2 = new ProfesorInputDto(contador, contador, "No tan bueno", "BackEnd");
        this.profesor2 = new Profesor(contador, persona2, "BackEnd", "DAM", null);
        this.estudianteIntroducido2 = new StudentInputDto(contador, contador, 33, "No tan bueno", "BackEnd");
        this.student2 = new Student(contador, 33, "No tan bueno", "BackEnd", null, null, null);
        persona2.setId_persona(contador);
        profesor2.setPersona(persona2);


        personaRepository.save(persona1);
        personaRepository.save(persona2);
        profesorRepository.save(profesor1);
        profesorRepository.save(profesor2);
        studentRepository.save(student);
        studentRepository.save(student2);
    }

    @Test
    void addProfesor() throws Exception {
        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona1);
        Mockito.when(studentRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(profesorRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(personaRepository.findById(profesorIntroducido1.getId_persona())).thenReturn(Optional.of(persona1));
        Mockito.when(profesorRepository.save(Mockito.any(Profesor.class))).thenReturn(profesor1);

        ProfesorOutputDto profesorDevuelto = profesorService.addProfesor(profesorIntroducido1);
        System.out.println(profesorDevuelto);
        assertNotNull(profesorDevuelto);
    }

    @Test
    void getAllProfesors() {
        List<Profesor> listaFicticia = new ArrayList<>();
        listaFicticia.add(profesor1);
        listaFicticia.add(profesor2);

        //Con esto hacemos las páginas según la lista
        Page<Profesor> profesorPage = new PageImpl<>(listaFicticia);

        Mockito.when(profesorRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(profesorPage);
        Mockito.when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona1));
        Mockito.when(personaRepository.findById(2)).thenReturn(Optional.ofNullable(persona2));

        // En el caso de la búsqueda
        Iterable<ProfesorSimpleOutputDto> resultado = profesorService.getAllProfesors(0, 25);
        List<ProfesorSimpleOutputDto> listaResultado = new ArrayList<>();
        for (Profesor profesorPasar : listaFicticia) {
            listaResultado.add(profesorPasar.profesorToSimpleOutputDto());
        }
        assertNotNull(listaResultado);
        assertEquals(listaFicticia.size(), listaResultado.size());
    }

    @Test
    void getProfesorByIdAndOutputType() {
        int id = profesor1.getIdProfesor();
        Mockito.when(profesorRepository.findById(id)).thenReturn(Optional.ofNullable(profesor1));

        Object profesorCompletoObjeto = profesorService.getProfesorByIdAndOutputType(id, "full");
        Object profesorSimpleObjeto = profesorService.getProfesorByIdAndOutputType(id, "simple");


        // Convertimos con ModelMapper los objetos a su tipo correcto
        ProfesorOutputDto profesorCompleto = modelMapper.map(profesorCompletoObjeto, ProfesorOutputDto.class);
        ProfesorSimpleOutputDto profesorSimple = modelMapper.map(profesorSimpleObjeto, ProfesorSimpleOutputDto.class);

        Mockito.verify(profesorRepository, times(2)).findById(id);

        assertNotNull(profesorSimple);
        assertNotNull(profesorCompleto);
    }

    @Test
    void addStudentToProfesor() {
        Set<Student> listaEstudiantes = new HashSet<>();
        listaEstudiantes.add(student);
        Profesor profesorConEstudiantes = profesor1;
        profesorConEstudiantes.setStudents(listaEstudiantes);

        Mockito.when(studentRepository.findById(student.getId_student())).thenReturn(Optional.ofNullable(student));
        Mockito.when(studentRepository.findById(student2.getId_student())).thenReturn(Optional.ofNullable(student2));
        Mockito.when(profesorRepository.findById(profesor1.getIdProfesor())).thenReturn(Optional.ofNullable(profesorConEstudiantes));
        student.setProfesor(profesor1);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when(profesorRepository.save(profesor1)).thenReturn(profesorConEstudiantes);

        profesorService.addStudentToProfesor(profesor1.getIdProfesor(), student.getId_student());

        Mockito.verify(profesorRepository, times(3)).save(Mockito.any(Profesor.class));
        Mockito.verify(studentRepository, times(3)).save(Mockito.any(Student.class));
        assertNotNull(profesor1.getStudents());
    }

    @Test
    void getProfesorWithStudents() {
        Set<Student> listaEstudiantes = new HashSet<>();
        listaEstudiantes.add(student);
        profesor1.setStudents(listaEstudiantes);
        student.setProfesor(profesor1);
        int id = profesor1.getIdProfesor();
        student.setPersona(persona2);

        Mockito.when(profesorRepository.findById(id)).thenReturn(Optional.ofNullable(profesor1));
        Mockito.when(studentRepository.findByIdProfesor(id)).thenReturn(listaEstudiantes);

        ProfesorWithStudentOutputDto profesorConEstudiantes = profesorService.getProfesorWithStudents(id);
        assertNotNull(profesorConEstudiantes);
    }

    @Test
    void updateProfesor() {
        int id = 1;
        ProfesorInputDto updatedProfesorInput = new ProfesorInputDto(1, 1, "Ha mejorado bastante", "BackEnd");
        Profesor updatedProfesor = new Profesor(updatedProfesorInput);
        updatedProfesor.setPersona(persona1);
        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona1);
        profesorRepository.save(profesor1);

        Mockito.when(profesorRepository.findById(id)).thenReturn(Optional.ofNullable(profesor1));
        Mockito.when(personaRepository.findById(updatedProfesorInput.getId_persona())).thenReturn(Optional.of(persona1));
        Mockito.when(profesorRepository.save(Mockito.any(Profesor.class))).thenReturn(updatedProfesor);

        ProfesorSimpleOutputDto profesorDevuelto = profesorService.updateProfesor(profesor1.getIdProfesor(), updatedProfesorInput);

        //Comprobamos que se hayan hecho l
        Mockito.verify(profesorRepository, times(4)).save(Mockito.any(Profesor.class));
        assertEquals(profesorDevuelto.getComments(), updatedProfesorInput.getComments());
    }

    @Test
    void deleteProfesorById() {
        int id = 1;
        Mockito.when(profesorRepository.findById(id)).thenReturn(Optional.ofNullable(profesor1));
        Mockito.when(studentRepository.findById(id)).thenReturn(null);

        profesorService.deleteProfesorById(profesorIntroducido1.getId_profesor());

        Mockito.verify(profesorRepository, times(1)).deleteById(Mockito.anyInt());
    }
}