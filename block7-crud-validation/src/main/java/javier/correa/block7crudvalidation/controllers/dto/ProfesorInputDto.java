package javier.correa.block7crudvalidation.controllers.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorInputDto {
    private int id_profesor;
    private int id_persona;
    private String comments;
    private String branch;

}
