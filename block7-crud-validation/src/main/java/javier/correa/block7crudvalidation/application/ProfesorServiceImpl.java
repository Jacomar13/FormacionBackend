package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.ProfesorOutputDto;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.exception.UnprocesableException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import javier.correa.block7crudvalidation.repository.ProfesorRepository;
import javier.correa.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new UnprocesableException("El profesor con id: " + profesorInputDto.getId_persona()+", ya existe",422);
        }

        Persona persona = personaRepository.findById(profesorInputDto.getId_persona()).orElseThrow();
        Profesor profesor = new Profesor(profesorInputDto);

        persona.setProfesor(profesor);
        profesor.setPersona(persona);

        return profesorRepository
                .save(profesor)
                .profesorToOutputDto();
    }

    @Override
    public Iterable<ProfesorOutputDto> getAllProfesors(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Object getProfesorByIdAndOutputType(int id, String outputType) {
        if (outputType.equals("full"))
            return profesorRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("No se ha encontrado el estudiante con id " + id, 404)).profesorToOutputDto();
        else if (outputType.equals("simple"))
            return profesorRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("No se ha encontrado el estudiante con id " + id, 404)).profesorToSimpleOutputDto();
        else
            return null;
    }
}
