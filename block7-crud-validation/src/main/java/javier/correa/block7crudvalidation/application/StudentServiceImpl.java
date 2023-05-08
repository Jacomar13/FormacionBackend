package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.StudentInputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.exception.UnprocesableException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import javier.correa.block7crudvalidation.repository.ProfesorRepository;
import javier.correa.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service

public class StudentServiceImpl implements StudentService{

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfesorRepository profesorRepository;
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

        Persona persona = personaRepository.findById(studentInputDto.getId_persona()).orElseThrow();
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
}
