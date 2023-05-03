package javier.correa.block7crudvalidation.controllers.dto;

import javier.correa.block7crudvalidation.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfesorSimpleOutputDto {
    private String id_profesor;
    private Persona persona;
    private String comments;
    private String branch;
}
