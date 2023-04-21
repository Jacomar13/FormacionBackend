package javier.correa.block7crud.controladores.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonaOutputDto {
    private int id;
    private String nombre;
    private String edad;
    private  String poblacion;
}
