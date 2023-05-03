package javier.correa.block7crudvalidation.controllers.dto;


import javier.correa.block7crudvalidation.domain.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorInputDto {
    private String id_profesor;
    private Persona persona;
    private String comments;
    private String branch;
}
