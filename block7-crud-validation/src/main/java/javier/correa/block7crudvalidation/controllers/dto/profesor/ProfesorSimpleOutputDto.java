package javier.correa.block7crudvalidation.controllers.dto.profesor;

import javier.correa.block7crudvalidation.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfesorSimpleOutputDto {
    private int id_profesor;
    private int id_persona;
    private String comments;
    private String branch;
}
