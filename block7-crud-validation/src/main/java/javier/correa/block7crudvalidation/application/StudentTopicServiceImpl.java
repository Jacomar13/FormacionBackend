package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.StudentTopic;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.exception.UnprocesableException;
import javier.correa.block7crudvalidation.repository.StudentTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentTopicServiceImpl implements StudentTopicService{
    @Autowired
    StudentTopicRepository studentTopicRepository;


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
    public Object getListOfTopicByStudent(int id_student) {
        return null;
    }

    @Override
    public void addTopicToStudent(int id_student, int id_topic) {

    }
}
