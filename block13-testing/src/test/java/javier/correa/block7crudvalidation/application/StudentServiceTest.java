package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import javier.correa.block7crudvalidation.repository.ProfesorRepository;
import javier.correa.block7crudvalidation.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    Persona persona;
    Persona persona2;

    Student student;
    Student student2;
    Integer contador = 1;
    PersonaInputDto personaIntroducida;
    StudentInputDto estudianteIntroducido1;
    StudentInputDto estudianteIntroducido2;

    @Mock
    PersonaRepository personaRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    ProfesorRepository profesorRepository;
    @InjectMocks
    private StudentServiceImpl studentService;
    @InjectMocks
    private PersonaServiceImpl personaService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona = new Persona(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date(), null, null);
        this.estudianteIntroducido1 = new StudentInputDto(contador, contador, 40, "Muy bueno", "ASIR");
        this.student = new Student(contador, 40, "Muy bueno", "ASIR", persona, null, null);
        persona.setId_persona(contador);
        contador = contador + 1;
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona2 = new Persona(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date(), null, null);
        this.estudianteIntroducido2 = new StudentInputDto(contador, contador, 33, "No tan bueno", "BackEnd");
        this.student2 = new Student(contador, 33, "No tan bueno", "BackEnd", null, null, null);
        persona2.setId_persona(contador);



        personaRepository.save(persona);
        personaRepository.save(persona2);
        studentRepository.save(student);
        studentRepository.save(student2);
    }

    @Test
    void addStudent() throws Exception {
        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona);
        Mockito.when(studentRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(profesorRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(personaRepository.findById(estudianteIntroducido1.getId_persona())).thenReturn(Optional.of(persona));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        StudentOutputDto estudianteDevuelto = studentService.addStudent(estudianteIntroducido1);
        assertNotNull(estudianteDevuelto);
    }


    @Test
    void getAllStudents() {
    }

    @Test
    void getStudentByIdAndOutputType() {
    }

    @Test
    void addTopicToStudent() {
    }

    @Test
    void removeTopicOfStudent() {
    }

    @Test
    void deleteStudentById() {
    }

    @Test
    void updateStudent() throws Exception {

        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona);
        Mockito.when(studentRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(profesorRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(personaRepository.findById(estudianteIntroducido1.getId_persona())).thenReturn(Optional.of(persona));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        studentService.addStudent(estudianteIntroducido1);

        Mockito.when(studentRepository.findByIdPersona(1)).thenReturn(student);
        Mockito.when(personaRepository.findById(estudianteIntroducido1.getId_persona())).thenReturn(Optional.of(persona));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        StudentSimpleOutputDto estudianteDevuelto = studentService.updateStudent(student.getId_student(), estudianteIntroducido1);
        assertNotNull(estudianteDevuelto);
    }
}