package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.student.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.StudentTopic;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.exception.UnprocesableException;
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
    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) throws Exception {

        Student studentExist= studentRepository.findByIdPersona(studentInputDto.getId_persona());
        if (studentExist != null) {
            throw new UnprocesableException("El estudiante con id: " + studentInputDto.getId_persona()+", ya existe",422);
        }

        Profesor profesorExists = profesorRepository.findByIdPersona(studentInputDto.getId_persona());
        if (profesorExists != null) {
            throw new UnprocesableException("La persona con id: " + studentInputDto.getId_persona()+", ya está asignada como un profesor",422);
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
            return studentRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("No se ha encontrado el estudiante con id " + id, 404)).studentToOutputDto();
        else if (outputType.equals("simple"))
            return studentRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("No se ha encontrado el estudiante con id " + id, 404)).studentSimpleToOutputDto();
        else
            return null;
    }

    @Override
    public void addTopicToStudent(int id_student, int id_study) {
        Student student = studentRepository.findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el estudiante con id " + id_student, 404));
        StudentTopic topic = studentTopicRepository.findById(id_study)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la/s asignatura/s con id " + id_study, 404));

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
    public void removeTopicOfStudent(int id_student, List<Integer> id_study) {
        Student student = studentRepository.findById(id_student)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el estudiante con id " + id_student, 404));
        for (int i = 0; i <id_study.size(); i++){
            StudentTopic topic = studentTopicRepository.findById(id_study.get(i))
                    .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la asignatura con id " + id_study, 404));

            topic.setStudent(null);
            studentTopicRepository.save(topic);
        }
    }

    @Override
    public void deleteStudentById(int id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el estudiante con id: " + id, 404));
        Set<StudentTopic> studentTopicsList = studentTopicRepository.findByIdStudent(id);
        if (!studentTopicsList.isEmpty()){
            throw new UnprocesableException("No puedes eliminar el estudiante con id: "+ id +" ya que tiene asginaturas",422);
        }
        Persona persona = student.getPersona();
        persona.setStudent(null);
        personaRepository.save(persona);
        studentRepository.deleteById(id);
    }



    @Override
    public StudentSimpleOutputDto updateStudent(int id, StudentInputDto studentInputDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El estudiante con id: " + id + ", no se ha encontrado", 404));
        Student updatedStudent = new Student(studentInputDto);
        int idPersona = studentInputDto.getId_persona();
        Optional<Persona> personaOpt = personaRepository.findById(idPersona);
        Persona persona = personaOpt.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con id " + id, 404));
        updatedStudent.setId_student(id);
        updatedStudent.setPersona(persona);
        return studentRepository.save(updatedStudent).studentSimpleToOutputDto();
    }
}
