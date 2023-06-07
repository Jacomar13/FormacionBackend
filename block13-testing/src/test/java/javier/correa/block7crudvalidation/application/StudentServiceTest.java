package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.StudentTopic;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import javier.correa.block7crudvalidation.repository.ProfesorRepository;
import javier.correa.block7crudvalidation.repository.StudentRepository;
import javier.correa.block7crudvalidation.repository.StudentTopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

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
    @Mock
    StudentTopicRepository studentTopicRepository;

    @InjectMocks
    private StudentServiceImpl studentService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona = new Persona(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date(), null, null);
        this.estudianteIntroducido1 = new StudentInputDto(contador, contador, 40, "Muy bueno", "ASIR");
        this.student = new Student(contador, 40, "Muy bueno", "ASIR", persona, null, null);
        persona.setId_persona(contador);
        student.setPersona(persona);
        contador = contador + 1;
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona2 = new Persona(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date(), null, null);
        this.estudianteIntroducido2 = new StudentInputDto(contador, contador, 33, "No tan bueno", "BackEnd");
        this.student2 = new Student(contador, 33, "No tan bueno", "BackEnd", null, null, null);
        persona2.setId_persona(contador);
        student2.setPersona(persona2);

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

        List<Student> listaFicticia = new ArrayList<>();
        listaFicticia.add(student);
        listaFicticia.add(student2);
        //Con esto hacemos las páginas según la lista
        Page<Student> studentPage = new PageImpl<>(listaFicticia);

        Mockito.when(studentRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(studentPage);
        Mockito.when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        Mockito.when(personaRepository.findById(2)).thenReturn(Optional.ofNullable(persona2));

        // En el caso de la búsqueda
        Iterable<StudentSimpleOutputDto> resultado = studentService.getAllStudents(0, 25);
        List<StudentSimpleOutputDto> listaResultado = new ArrayList<>();
        for (Student studentPasar : listaFicticia) {
            listaResultado.add(studentPasar.studentSimpleToOutputDto());
        }
        assertNotNull(listaResultado);
        assertEquals(listaFicticia.size(), listaResultado.size());
    }

    @Test
    void getStudentByIdAndOutputType() {
    }

    @Test
    void addTopicToStudent() {
        /*Set<Student> listaEstudiantes = new HashSet<>();
        listaEstudiantes.add(student);
        Profesor profesorConEstudiantes = profesor1;
        profesorConEstudiantes.setStudents(listaEstudiantes);

        Mockito.when(studentRepository.findById(student.getId_student())).thenReturn(Optional.ofNullable(student));
        Mockito.when(studentRepository.findById(student2.getId_student())).thenReturn(Optional.ofNullable(student2));
        Mockito.when(profesorRepository.findById(profesor1.getIdProfesor())).thenReturn(Optional.ofNullable(profesorConEstudiantes));
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when(profesorRepository.save(profesor1)).thenReturn(profesor1);

        profesorService.addStudentToProfesor(profesor1.getIdProfesor(), student.getId_student());

        Mockito.verify(profesorRepository, times(3)).save(Mockito.any(Profesor.class));
        Mockito.verify(studentRepository, times(3)).save(Mockito.any(Student.class));
        assertNotNull(profesor1.getStudents());*/
    }

    @Test
    void removeTopicOfStudent() {
    }

    @Test
    void deleteStudentById() {
        int id = 1;
        Set<StudentTopic> studentTopicsList = new HashSet<>();
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(student));
        Mockito.when(profesorRepository.findById(id)).thenReturn(null);
        Mockito.when(studentTopicRepository.findByIdStudent(id)).thenReturn(studentTopicsList);

        studentService.deleteStudentById(estudianteIntroducido1.getId_student());

        Mockito.verify(studentRepository, times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void updateStudent() throws Exception {
        int id = 1;
        StudentInputDto updatedStudentInput = new StudentInputDto(1, 1, 27, "Ha empeorado", "DAW");
        Student updatedStudent = new Student( updatedStudentInput);
        updatedStudent.setPersona(persona);
        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(persona);
        studentRepository.save(student);

        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(student));
        Mockito.when(personaRepository.findById(updatedStudentInput.getId_persona())).thenReturn(Optional.of(persona));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(updatedStudent);

        StudentSimpleOutputDto estudianteDevuelto = studentService.updateStudent(student.getId_student(), updatedStudentInput);

        Mockito.verify(studentRepository, times(4)).save(Mockito.any(Student.class));
        assertEquals(estudianteDevuelto.getComments(), updatedStudentInput.getComments());
    }
}