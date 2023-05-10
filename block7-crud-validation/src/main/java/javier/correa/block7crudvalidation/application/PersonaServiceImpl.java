package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaInputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.persona.PersonaProfesorWithStudentsOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.student.StudentSimpleOutputDto;
import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import javier.correa.block7crudvalidation.domain.exception.EntityNotFoundException;
import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.exception.UnprocesableException;
import javier.correa.block7crudvalidation.repository.PersonaRepository;
import javier.correa.block7crudvalidation.repository.ProfesorRepository;
import javier.correa.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws UnprocesableException {
        if (persona.getUsuario()== null) {throw new UnprocesableException("El usuario no puede estar vacío", 422);}
        if (persona.getUsuario().length() < 6 || persona.getUsuario().length() > 10){throw new UnprocesableException("La longitud de usuario no pueder ser inferior a 6 ni superior a 10 caracteres", 422);}
        if (Objects.isNull(persona.getPassword()) || persona.getPassword().isBlank()) {throw new UnprocesableException("La contraseña no puede estar vacía", 422);}
        if (persona.getName() == null) {throw new UnprocesableException("El nombre no puede estar vacío", 422);}
        if (persona.getCompany_email() == null) {throw new UnprocesableException("El correo profesional no puede estar vacío", 422);}
        if (persona.getPersonal_email() == null) {throw new UnprocesableException("El correo personal no puede estar vacío", 422);}
        if (persona.getCity() == null) {throw new UnprocesableException("La ciudad no puede estar vacía", 422);}
        if (persona.isActive() == false) {throw new UnprocesableException("El usuario no puede estar NO ACTIVADO", 422);}
        if (persona.getCreated_date() == null) {throw new UnprocesableException("La fecha de creación no puede estar vacía", 422);}
        return personaRepository.save(new Persona(persona)).personaToOutputDto();
    }

    @Override
    public Object getPersonaById(Integer id, String personType) throws EntityNotFoundException {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        Persona persona = personaOptional.orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la persona con id " + id, 404));

        Student studentExists= studentRepository.findByIdPersona(id);
        Profesor profesorExists = profesorRepository.findByIdPersona(id);

        if (studentExists != null && personType.equals("student")) {
            StudentOutputDto studentOutputDto = studentExists.studentToOutputDto();
            return studentOutputDto;
        }
        else if (studentExists == null && profesorExists!= null && personType.equals("student")){
            throw new EntityNotFoundException("El estudiante con id: " + id +" que estás buscando, es un profesor",404);
        }
        else if (studentExists == null && profesorExists== null && personType.equals("student")){
            throw new EntityNotFoundException("El estudiante con id: " + id +" que estás buscando, todavía no está asignado",404);
        }
        else if (profesorExists == null && studentExists== null && personType.equals("profesor")){
            throw new EntityNotFoundException("El profesor con id: " + id +" que estás buscando, todavía no está asignado",404);
        }
        else if (profesorExists != null && personType.equals("profesor")) {
            int id_profesor = profesorExists.getIdProfesor();
            List<StudentSimpleOutputDto> estudiantes = studentRepository.findByIdProfesor(id_profesor).stream().map(Student::studentSimpleToOutputDto).toList();
            Set<StudentSimpleOutputDto> estud = new HashSet<>(estudiantes);

            ProfesorWithStudentOutputDto profesor =new ProfesorWithStudentOutputDto(profesorExists.getIdProfesor(),
                    profesorExists.getComments(), profesorExists.getBranch(), estud);
            return new PersonaProfesorWithStudentsOutputDto(persona.getId_persona(), persona.getUsuario(), persona.getName(), persona.getSurname(),
                    persona.getCompany_email(), persona.getPersonal_email(), persona.getCity(), persona.isActive(), persona.getCreated_date(),
                    persona.getImagen_url(), persona.getTermination_date(), profesor);
        }
        else if (profesorExists == null && personType.equals("profesor")) {
            throw new EntityNotFoundException("El profesor con id: " + id +" que estás buscando, es un estudiante",404);
        }
        else {
            PersonaOutputDto personaOutputDto = persona.personaToOutputDto();
            return personaOutputDto;
        }
    }


    @Override
    public List<PersonaOutputDto> getPersonabyUsuario(String user) throws EntityNotFoundException {
        List<Persona> listPersona = personaRepository.findByUsuario(user);

        if (listPersona.isEmpty())
            throw new EntityNotFoundException("No se ha encontrado ninguna persona con el nombre de usuario: " + user, 404);

        List<PersonaOutputDto> personaOutputDtoList = listPersona.stream()
                .map(Persona::personaToOutputDto)
                .toList();
        return personaOutputDtoList;
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) throws EntityNotFoundException{
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        if (personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToOutputDto)
                .toList().isEmpty())
                    throw new EntityNotFoundException("No hay ninguna persona registrada en la base de datos actualmente",404);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::personaToOutputDto)
                .toList();
    }


    @Override
    public void deletePersonaById(int id) throws UnprocesableException {
        personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encontrada", 404));
        Student studentExist= studentRepository.findByIdPersona(id);
        if (studentExist != null) {
            throw new UnprocesableException("Esta persona ya es estudiante, si deseas eliminar esta persona, debes eliminar antes el estudiante", 422);
        }
        personaRepository.deleteById(id);
    }

    @Override
    public PersonaOutputDto updatePersona(int id, PersonaInputDto personaInputDto) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada", 404));
        Persona updatedPersona = new Persona(personaInputDto);
        updatedPersona.setId_persona(id);
        return personaRepository.save(updatedPersona).personaToOutputDto();
    }
}
