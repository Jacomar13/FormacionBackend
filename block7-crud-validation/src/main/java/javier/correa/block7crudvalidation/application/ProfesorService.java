package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.*;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) throws Exception;

    Iterable<ProfesorOutputDto> getAllProfesors(int pageNumber, int pageSize);

    Object getProfesorByIdAndOutputType(int id, String outputType);
}
