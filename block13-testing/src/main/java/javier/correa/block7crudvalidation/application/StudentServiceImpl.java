package javier.correa.block7crudvalidation.application;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service

public class StudentServiceImpl implements StudentService{

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    StudentTopicRepository studentTopicRepository;

    String studentNotFoundMessage = "No se ha encontrado el estudiante con id ";
    String topicNotFoundMessage = "No se ha encontrado la asignatura con id ";
    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) throws Exception {

        Student studentExist= studentRepository.findByIdPersona(studentInputDto.getId_persona());
        if (studentExist != null) {
            throw new UnprocessableException("El estudiante con id: " + studentInputDto.getId_persona()+", ya existe",422);
        }

        Profesor profesorExists = profesorRepository.findByIdPersona(studentInputDto.getId_persona());
        if (profesorExists != null) {
            throw new UnprocessableException("La persona con id: " + studentInputDto.getId_persona()+", ya está asignada como un profesor",422);
        }
        Persona persona = personaRepository.findById(studentInputDto.getId_persona()).orElseThrow(() -> new EntityNotFoundException("Antes de crear el estudiante, asegurate de haber elegido una persona existente", 404));
        Student student = new Student(studentInputDto);

        persona.setStudent(student);
        student.setPersona(persona);

        return studentRepository
                .save(student)
                .studentToOutputDto();
    }

    @Override
    public Object getStudentByIdAndOutputType(int id, String outputType) {

        if (outputType.equals("full"))
            return studentRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(studentNotFoundMessage + id, 404)).studentToOutputDto();
        else if (outputType.equals("simple"))
            return studentRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(studentNotFoundMessage + id, 404)).studentSimpleToOutputDto();
        else
            throw new UnprocessableException("El formato de salida tiene que ser 'full' o 'simple'", 422);
    }

    @Override
    public void addTopicToStudent(int idStudent, int idStudy) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException(studentNotFoundMessage + idStudent, 404));
        StudentTopic topic = studentTopicRepository.findById(idStudy)
                .orElseThrow(() -> new EntityNotFoundException(topicNotFoundMessage + idStudy, 404));
        if (topic.getStudent() == student){
            throw new UnprocessableException("El estudiante al que intentas asignar la asignatura ya la tiene", 422);
        }

        topic.setStudent(student);
        student.getEstudios().add(topic);

        studentTopicRepository.save(topic);
        studentRepository.save(student);
    }


    @Override
    public Iterable<StudentSimpleOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        if (studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentSimpleToOutputDto)
                .toList().isEmpty())
            throw new EntityNotFoundException("No hay ningun estudiante registrado en la base de datos actualmente",404);
        return studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentSimpleToOutputDto)
                .toList();
    }

    @Override
    public void removeTopicOfStudent(int idStudent, List<Integer> idStudy) {
        if (idStudy.isEmpty()){
            throw new UnprocessableException("La lista no puede ser nula", 422);
        }
        Student student =studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException(studentNotFoundMessage + idStudent, 404));
        for (int i = 0; i < idStudy.size(); i++){
            StudentTopic topic = studentTopicRepository.findById(idStudy.get(i))
                    .orElseThrow(() -> new EntityNotFoundException(topicNotFoundMessage + idStudy, 404));

            student.setEstudios(null);
            topic.setStudent(null);
            studentRepository.save(student);
            studentTopicRepository.save(topic);
        }
    }

    @Override
    public void deleteStudentById(int id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(studentNotFoundMessage + id, 404));
        Set<StudentTopic> studentTopicsList = studentTopicRepository.findByIdStudent(id);
        if (!studentTopicsList.isEmpty()){
            throw new UnprocessableException("No puedes eliminar el estudiante con id: "+ id +" ya que tiene asginaturas",422);
        }
        Persona persona = student.getPersona();
        persona.setStudent(null);
        personaRepository.save(persona);
        studentRepository.deleteById(id);
    }



    @Override
    public StudentSimpleOutputDto updateStudent(int id, StudentInputDto studentInputDto) {
        studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(studentNotFoundMessage + id, 404));
        Student updatedStudent = new Student(studentInputDto);
        int idPersona = studentInputDto.getId_persona();
        Optional<Persona> personaOpt = personaRepository.findById(idPersona);
        Persona persona = personaOpt.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con id " + id, 404));
        updatedStudent.setId_student(id);
        updatedStudent.setPersona(persona);
        return studentRepository.save(updatedStudent).studentSimpleToOutputDto();
    }
}
