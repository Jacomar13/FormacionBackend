package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorSimpleOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;


    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) throws Exception {
        Student studentExist= studentRepository.findByIdPersona(profesorInputDto.getId_persona());
        if (studentExist != null) {
            throw new UnprocesableException("La persona con id: " + profesorInputDto.getId_persona()+", ya está asignada como un estudiante",422);
        }

        Profesor profesorExists = profesorRepository.findByIdPersona(profesorInputDto.getId_persona());
        if (profesorExists != null) {
            throw new UnprocesableException("El profesor con id: " + profesorInputDto.getId_persona() +", ya existe",422);
        }

        Persona persona = personaRepository.findById(profesorInputDto.getId_persona())
                .orElseThrow(() -> new EntityNotFoundException("Antes de crear el profesor, asegurate de haber elegido una persona existente", 404));
        Profesor profesor = new Profesor(profesorInputDto);

        persona.setProfesor(profesor);
        profesor.setPersona(persona);

        return profesorRepository
                .save(profesor)
                .profesorToOutputDto();
    }



    @Override
    public Iterable<ProfesorSimpleOutputDto> getAllProfesors(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        if (profesorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profesor::profesorToSimpleOutputDto)
                .toList().isEmpty())
            throw new EntityNotFoundException("No hay ningun estudiante registrado en la base de datos actualmente",404);
        return profesorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profesor::profesorToSimpleOutputDto)
                .toList();
    }

    @Override
    public Object getProfesorByIdAndOutputType(int id, String outputType) {
        String notFoundProfesor = "No se ha encontrado el profesor con id ";
        if (outputType.equals("full"))
            return profesorRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(notFoundProfesor + id, 404)).profesorToOutputDto();
        else if (outputType.equals("simple"))
            return profesorRepository.findById(id).orElseThrow(() ->new EntityNotFoundException(notFoundProfesor + id, 404)).profesorToSimpleOutputDto();
        else
            return new EntityNotFoundException("Los dos tipos de salida son 'full' y 'simple', no se permite nulo ni otra palabra", 404);
    }

    @Override
    public void addStudentToProfesor(int idProfesor, int idStudent) {
        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el estudiante con id: " + idStudent, 404));
        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el estudiante con id: " + idProfesor, 404));

        student.setProfesor(profesor);
        profesor.getStudents().add(student);

        studentRepository.save(student);
        profesorRepository.save(profesor);

    }

    @Override
    public ProfesorWithStudentOutputDto getProfesorWithStudents(int idProfesor) {
        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el profesor con id " + idProfesor, 404));


        List<StudentSimpleOutputDto> estudiantes = studentRepository.findByIdProfesor(idProfesor).stream().map(Student::studentSimpleToOutputDto).toList();
        Set<StudentSimpleOutputDto> estud = new HashSet<>(estudiantes);

        return new ProfesorWithStudentOutputDto(profesor.getIdProfesor(),
                profesor.getComments(), profesor.getBranch(), estud);
    }

    @Override
    public ProfesorSimpleOutputDto updateProfesor(int id, ProfesorInputDto profesorInputDto) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El profesor con id: " + id + ", no se ha encontrado", 404));
        Profesor updatedProfesor = new Profesor(profesorInputDto);
        int idPersona = profesor.getIdProfesor();
        Optional<Persona> personaOpt = personaRepository.findById(idPersona);
        Persona persona = personaOpt.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con id " + id, 404));
        updatedProfesor.setIdProfesor(id);
        updatedProfesor.setPersona(persona);
        return profesorRepository.save(updatedProfesor).profesorToSimpleOutputDto();
    }

    @Override
    public void deleteProfesorById(int id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el profesor con id: " + id, 404));
        Set<Student> studentList = studentRepository.findByIdProfesor(id);
        if (!studentList.isEmpty()){
            throw new UnprocesableException("No puedes eliminar profesor ya que tiene estudiantes",422);

        }
        Persona persona = profesor.getPersona();
        persona.setProfesor(null);
        personaRepository.save(persona);
        profesorRepository.deleteById(id);
    }
}
