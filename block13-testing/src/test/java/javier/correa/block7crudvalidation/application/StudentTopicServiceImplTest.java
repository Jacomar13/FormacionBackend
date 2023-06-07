package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.student.StudentWithTopicsOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;

import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.StudentTopic;
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

import static org.junit.jupiter.api.Assertions.*;

class StudentTopicServiceImplTest {


    int contador = 1;
    StudentTopic estudio1;
    StudentTopic estudio2;
    Student student1;
    Student student2;
    StudentTopicInputDto estudioIntroducido1;
    StudentTopicInputDto estudioIntroducido2;
    StudentWithTopicsOutputDto estudianteConAsignaturas;
    @Mock
    StudentTopicRepository studentTopicRepository;
    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    StudentTopicServiceImpl studentTopicService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

        this.student1 = new Student(contador, 40, "Muy bueno", "ASIR", null, null, null);
        this.estudioIntroducido1 = new StudentTopicInputDto(contador, "Matemáticas", "No lo aprueba casi nadie", new Date(), new  Date());
        this.estudio1= new StudentTopic(contador, null, "Matemáticas", "No lo aprueba casi nadie", new Date(), new Date());

        contador = contador + 1;
        this.student2 = new Student(contador, 33, "No tan bueno", "BackEnd", null, null, null);
        this.estudioIntroducido2 = new StudentTopicInputDto(contador, "Lengua", "Es bastante fácil", new Date(), new  Date());
        this.estudio2= new StudentTopic(contador, null, "Lengua", "Es bastante fácil", new Date(), new Date());

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentTopicRepository.save(estudio1);
        studentTopicRepository.save(estudio2);
    }

    @Test
    void addTopic() throws Exception {
        Mockito.when(studentTopicRepository.save(Mockito.any(StudentTopic.class))).thenReturn(estudio1);

        studentTopicService.addTopic(estudioIntroducido1);
        Mockito.verify(studentTopicRepository, Mockito.times(3)).save(Mockito.any(StudentTopic.class));
    }

    @Test
    void getAllTopics() {
        List<StudentTopic> listaFicticia = new ArrayList<>();
        listaFicticia.add(estudio1);
        listaFicticia.add(estudio2);
        //Con esto hacemos las páginas según la lista
        Page<StudentTopic> topicPage = new PageImpl<>(listaFicticia);

        Mockito.when(studentTopicRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(topicPage);

        Iterable<StudentTopicOutputDto> resultado = studentTopicService.getAllTopics(0, 25);
        List<StudentTopicOutputDto> listaResultado = new ArrayList<>();
        for (StudentTopicOutputDto topicResultado : resultado){
            listaResultado.add(topicResultado);
        }
        assertNotNull(listaResultado);
        assertEquals(listaFicticia.size(), listaResultado.size());
    }

    @Test
    void getListOfTopicByStudent() {

        Set<StudentTopic> listaDeEstudiosDeEstudiantes =new HashSet<>();
        listaDeEstudiosDeEstudiantes.add(estudio1);


        student1.setEstudios(listaDeEstudiosDeEstudiantes);

        Mockito.when(studentRepository.findById(student1.getId_student())).thenReturn(Optional.ofNullable(student1));
        Mockito.when(studentTopicRepository.findByIdStudent(student1.getId_student())).thenReturn(listaDeEstudiosDeEstudiantes);

        StudentWithTopicsOutputDto estudianteResultado = studentTopicService.getListOfTopicByStudent(student1.getId_student());
        System.out.println();

        assertNotNull(estudianteResultado);

    }

    @Test
    void deleteTopicById() {
        Mockito.when(studentTopicRepository.findById(estudio1.getId_study())).thenReturn(Optional.ofNullable(estudio1));

        studentTopicService.deleteTopicById(estudio1.getId_study());
        Mockito.verify(studentTopicRepository, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void updateTopic() {
        estudioIntroducido1.setComment("Ahora es la asignatura más aprobada");
        StudentTopic topicAPasar = new StudentTopic(estudioIntroducido1);
        Mockito.when(studentTopicRepository.findById(estudio1.getId_study())).thenReturn(Optional.ofNullable(estudio1));

        Mockito.when(studentTopicRepository.save(Mockito.any(StudentTopic.class))).thenReturn(topicAPasar);
        StudentTopicOutputDto topicDevuelto = studentTopicService.updateTopic(estudio1.getId_study(), estudioIntroducido1);

        assertNotEquals(topicDevuelto.getComment(), estudio1.getComment());
        assertEquals(topicDevuelto.getComment(), estudioIntroducido1.getComment());

    }

    @Test
    void getTopicById() {
        Mockito.when(studentTopicRepository.findById(estudio1.getId_study())).thenReturn(Optional.ofNullable(estudio1));

        StudentTopicOutputDto topicDevuelto = studentTopicService.getTopicById(estudio1.getId_study());
        assertNotNull(topicDevuelto);
    }
}