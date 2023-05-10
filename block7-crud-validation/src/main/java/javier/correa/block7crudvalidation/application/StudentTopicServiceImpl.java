package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaProfesorWithStudentsOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentWithTopicsOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.StudentTopic;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.exception.UnprocesableException;
import javier.correa.block7crudvalidation.repository.StudentRepository;
import javier.correa.block7crudvalidation.repository.StudentTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentTopicServiceImpl implements StudentTopicService{
    @Autowired
    StudentTopicRepository studentTopicRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public StudentTopicOutputDto addTopic(StudentTopicInputDto studentTopicInputDto) throws Exception {
        if (studentTopicInputDto.getAsignatura() == null) {throw new UnprocesableException("El nombre de la asignatura no puede estar vacía", 422);}
        if (studentTopicInputDto.getComment() == null){throw new UnprocesableException("La longitud de usuario no pueder ser inferior a 6 ni superior a 10 caracteres", 422);}
        return studentTopicRepository.save(new StudentTopic(studentTopicInputDto)).studentTopicToOutputDto();
    }

    @Override
    public Iterable<StudentTopicOutputDto> getAllTopics(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        if (studentTopicRepository.findAll(pageRequest).getContent()
                .stream()
                .map(StudentTopic::studentTopicToOutputDto)
                .toList().isEmpty())
            throw new EntityNotFoundException("No hay ninguna asignatura registrada en la base de datos actualmente",404);
        return studentTopicRepository.findAll(pageRequest).getContent()
                .stream()
                .map(StudentTopic::studentTopicToOutputDto)
                .toList();
    }

    @Override
    public StudentWithTopicsOutputDto getListOfTopicByStudent(int id_student) {
        Student student = studentRepository.findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el estudiante con id " + id_student, 404));

        List<StudentTopicOutputDto> topics = studentTopicRepository.findByIdStudent(id_student).stream().map(StudentTopic::studentTopicToOutputDto).toList();
        Set<StudentTopicOutputDto> estud = new HashSet<>(topics);

        return new StudentWithTopicsOutputDto(student.getId_student(), student.getNum_hours_week(),
                student.getComments(), student.getBranch(), estud);
    }

    @Override
    public void deleteTopicById(int id) {
        StudentTopic topic = studentTopicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la asignatura con id: " + id, 404));
        studentTopicRepository.deleteById(id);
    }

    @Override
    public StudentTopicOutputDto updateTopic(int id, StudentTopicInputDto studentTopicInputDto) {
        StudentTopic student = studentTopicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La asignatura con id: " + id + ", no se ha encontrado", 404));
        StudentTopic updatedTopic = new StudentTopic(studentTopicInputDto);
        return studentTopicRepository.save(updatedTopic).studentTopicToOutputDto();
    }

    @Override
    public Object getTopicById(Integer id) {
        Optional<StudentTopic> topicOpt = studentTopicRepository.findById(id);
        StudentTopic topic = topicOpt.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la asignatura con id " + id, 404));

            StudentTopicOutputDto studentTopicOutputDto = topic.studentTopicToOutputDto();
            return studentTopicOutputDto;
    }
}
