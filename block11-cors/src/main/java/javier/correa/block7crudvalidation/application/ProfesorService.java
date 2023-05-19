package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorSimpleOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) throws Exception;

    Iterable<ProfesorSimpleOutputDto> getAllProfesors(int pageNumber, int pageSize);

    Object getProfesorByIdAndOutputType(int id, String outputType);

    void addStudentToProfesor(int id_profesor, int id_student);

    ProfesorWithStudentOutputDto getProfesorWithStudents(int id_profesor);
    ProfesorSimpleOutputDto updateProfesor(int id, ProfesorInputDto profesorInputDto);
    void deleteProfesorById(int id);
}
