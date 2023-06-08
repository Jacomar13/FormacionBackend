package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;

import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.StudentTopic;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.exception.UnprocessableException;
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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;


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
    StudentTopic estudio1;
    StudentTopic estudio2;

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
        this.estudio1= new StudentTopic(contador, null, "Matemáticas", "No lo aprueba casi nadie", new Date(), new Date());
        persona.setId_persona(contador);
        student.setPersona(persona);
        contador = contador + 1;
        this.personaIntroducida =  new PersonaInputDto(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date());
        this.persona2 = new Persona(contador, "Javier03", "password03", "nombre1", "surname1", "companyemail", "personalemail", "city", true, new Date(), "urlImagen", new Date(), null, null);
        this.estudianteIntroducido2 = new StudentInputDto(contador, contador, 33, "No tan bueno", "BackEnd");
        this.student2 = new Student(contador, 33, "No tan bueno", "BackEnd", null, null, null);
        this.estudio2= new StudentTopic(contador, null, "Lengua", "Es bastante fácil", new Date(), new Date());
        persona2.setId_persona(contador);
        student2.setPersona(persona2);

        personaRepository.save(persona);
        personaRepository.save(persona2);
        studentRepository.save(student);
        studentRepository.save(student2);
        studentTopicRepository.save(estudio1);
        studentTopicRepository.save(estudio2);
    }

    @Test
    void addStudent() throws Exception {
        // Caso cuando estudiante no existe
        // Cuando busque por estudiante o profesor que sean nulos para que no salte la excepción
        Mockito.when(studentRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(profesorRepository.findByIdPersona(1)).thenReturn(null);
        Mockito.when(personaRepository.findById(estudianteIntroducido1.getId_persona())).thenReturn(Optional.of(persona));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        // Añadimos un estudiante con el servicio
        StudentOutputDto estudianteDevuelto = studentService.addStudent(estudianteIntroducido1);

        // Comprobamos que el estudiante devuelto no sea nulo
        assertNotNull(estudianteDevuelto);

        // Caso cuando persona ya es profesor
        Mockito.when(studentRepository.findByIdPersona(2)).thenReturn(null);
        Mockito.when(profesorRepository.findByIdPersona(2)).thenReturn(new Profesor());
        assertThrows(UnprocessableException.class, () -> studentService.addStudent(estudianteIntroducido2));

        // Caso cuando ya es estudiante
        Mockito.when(studentRepository.findByIdPersona(2)).thenReturn(student2);
        assertThrows(UnprocessableException.class, () -> studentService.addStudent(estudianteIntroducido2));
    }


    @Test
    void getAllStudents() {

        // Caso en el que la lista está vacía
        List<Student> listaFicticia = new ArrayList<>();

        //Con esto hacemos las páginas según la lista
        Page<Student> studentPage;

        studentPage = new PageImpl<>(listaFicticia);
        Mockito.when(studentRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(studentPage);
        assertThrows(EntityNotFoundException.class, () -> studentService.getAllStudents(0, 25));


        //Caso en el que la lista no está vacía
        listaFicticia.add(student);
        listaFicticia.add(student2);
        studentPage = new PageImpl<>(listaFicticia);

        Mockito.when(studentRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(studentPage);
        Mockito.when(personaRepository.findById(1)).thenReturn(Optional.ofNullable(persona));
        Mockito.when(personaRepository.findById(2)).thenReturn(Optional.ofNullable(persona2));

        // En el caso de la búsqueda
        Iterable<StudentSimpleOutputDto> resultado = studentService.getAllStudents(0, 25);
        List<StudentSimpleOutputDto> listaResultado = new ArrayList<>();
        for (StudentSimpleOutputDto studentPasar : resultado) {
            listaResultado.add(studentPasar);
        }
        assertNotNull(listaResultado);
        assertEquals(listaFicticia.size(), listaResultado.size());

    }

    @Test
    void getStudentByIdAndOutputType() {

        // Caso en el que el estudiante existe y las dos salidas están correctas
        int id = student.getId_student();
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(student));

        Object estudianteCompletoObjeto = studentService.getStudentByIdAndOutputType(id, "full");
        Object estudianteSimpleObjeto = studentService.getStudentByIdAndOutputType(id, "simple");
        ModelMapper modelMapper = new ModelMapper();

        // Convertimos con ModelMapper los objetos a su tipo correcto
        StudentOutputDto estudianteCompleto = modelMapper.map(estudianteCompletoObjeto, StudentOutputDto.class);
        StudentSimpleOutputDto estudianteSimple = modelMapper.map(estudianteSimpleObjeto, StudentSimpleOutputDto.class);

        Mockito.verify(studentRepository, times(2)).findById(id);

        assertNotNull(estudianteSimple);
        assertNotNull(estudianteCompleto);

        //Caso en el que estudiante existe pero la salida no está correcta
        assertThrows(UnprocessableException.class, () -> studentService.getStudentByIdAndOutputType(id, "hola"));

        //Caso en el que los formatos de salida son correctos pero el estudiante no existe
        int id2 = 2;
        Mockito.when(studentRepository.findById(id2)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.getStudentByIdAndOutputType(id2, "full"));
        assertThrows(EntityNotFoundException.class, () -> studentService.getStudentByIdAndOutputType(id2, "simple"));
    }

    @Test
    void addTopicToStudent() {

        // Caso en el que estudiante existe, asignatura también, y se le ha asignado ya una asignatura
        Set<StudentTopic> listaEstudios = new HashSet<>();
        listaEstudios.add(estudio1);
        Student estudianteConEstudios = student;
        estudianteConEstudios.setEstudios(listaEstudios);

        int idEstudiante = student.getId_student();
        int idEstudio = estudio1.getId_study();


        Mockito.when(studentRepository.findById(idEstudiante)).thenReturn(Optional.of(estudianteConEstudios));
        Mockito.when(studentTopicRepository.findById(idEstudio)).thenReturn(Optional.ofNullable(estudio1));
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(estudianteConEstudios);
        Mockito.when(studentTopicRepository.save(Mockito.any(StudentTopic.class))).thenReturn(estudio1);

        studentService.addTopicToStudent(idEstudiante, idEstudio);

        Mockito.verify(studentRepository, times(3)).save(Mockito.any(Student.class));
        Mockito.verify(studentTopicRepository, times(3)).save(Mockito.any(StudentTopic.class));

        // Caso en el que ya se le ha asignado una asignatura a estudiante
        assertThrows(UnprocessableException.class, () -> studentService.addTopicToStudent(idEstudiante, idEstudio));

        // Caso en el que la asignatura no existe
        Mockito.when(studentRepository.findById(2)).thenReturn(Optional.of(student2));
        Mockito.when(studentTopicRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.addTopicToStudent(2, 2));

        // Caso en el que el estudiante no existe
        Mockito.when(studentRepository.findById(3)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.addTopicToStudent(3, 3));


    }

    @Test
    void removeTopicOfStudent() {

        //Caso en el que el estudiante existe y las asignaturas existen
        List<Integer> listaIdsEstudios= new ArrayList<>();
        listaIdsEstudios.add(estudio1.getId_study());

        Mockito.when(studentRepository.findById(student.getId_student())).thenReturn(Optional.ofNullable(student));
        Mockito.when(studentTopicRepository.findById(estudio1.getId_study())).thenReturn(Optional.ofNullable(estudio1));

        student.setEstudios(null);
        estudio1.setStudent(null);
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Mockito.when(studentTopicRepository.save(Mockito.any(StudentTopic.class))).thenReturn(estudio1);

        studentService.removeTopicOfStudent(student.getId_student(), listaIdsEstudios);

        Mockito.verify(studentRepository, times(3)).save(Mockito.any(Student.class));
        Mockito.verify(studentTopicRepository, times(3)).save(Mockito.any(StudentTopic.class));

        // Caso en el que el estudiante existe pero no la asignatura
        List<Integer> listaIdsEstudiosNulo= new ArrayList<>();
        listaIdsEstudiosNulo.add(estudio2.getId_study());

        Mockito.when(studentRepository.findById(2)).thenReturn(Optional.ofNullable(student2));
        Mockito.when(studentTopicRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.removeTopicOfStudent(2, listaIdsEstudiosNulo));

        // Caso en el que estudiante no existe
        Mockito.when(studentRepository.findById(3)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.removeTopicOfStudent(3, listaIdsEstudiosNulo));

        // Caso en el que la lista de IDs está vacía
        List<Integer> listaVacia= new ArrayList<>();
        assertThrows(UnprocessableException.class, () -> studentService.removeTopicOfStudent(4, listaVacia));
    }

    @Test
    void deleteStudentById() {

        //Caso En el que encuentra un estudiante sin alumnos
        int id = 1;
        Set<StudentTopic> studentTopicsListEmpty = new HashSet<>();
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(student));
        Mockito.when(studentTopicRepository.findByIdStudent(id)).thenReturn(studentTopicsListEmpty);

        studentService.deleteStudentById(estudianteIntroducido1.getId_student());

        Mockito.verify(studentRepository, times(1)).deleteById(Mockito.anyInt());

        //Caso en el que la lista tiene asignaturas

        Set<StudentTopic> studentTopicsList = new HashSet<>();
        studentTopicsList.add(estudio1);
        Mockito.when(studentRepository.findById(2)).thenReturn(Optional.ofNullable(student2));
        Mockito.when(studentTopicRepository.findByIdStudent(2)).thenReturn(studentTopicsList);

        assertThrows(UnprocessableException.class, () -> studentService.deleteStudentById(2));

        // Caso en el que el estudiante no es encontrado
        Mockito.when(studentRepository.findById(3)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> studentService.deleteStudentById(3));

    }

    @Test
    void updateStudent() throws Exception {

        // Caso en el que el estudiante existe y es persona
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

        // Caso en el que estudiante existe pero como persona no existe

        StudentInputDto updatedStudentInputNotPersona = new StudentInputDto(2, 2, 27, "Ha empeorado", "DAW");
        Mockito.when(studentRepository.findById(2)).thenReturn(Optional.ofNullable(student2));
        Mockito.when(personaRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.updateStudent(2, updatedStudentInputNotPersona));

        // Caso en el que el estudiante no existe
        Mockito.when(studentRepository. findById(3)).thenReturn(Optional.empty());
        StudentInputDto notStudent = new StudentInputDto(3, 3, 27, "Ha empeorado", "DAW");
        assertThrows(EntityNotFoundException.class, () -> studentService.updateStudent(2, notStudent));

    }
}