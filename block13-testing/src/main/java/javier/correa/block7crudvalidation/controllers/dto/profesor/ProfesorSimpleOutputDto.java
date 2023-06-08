package javier.correa.block7crudvalidation.controllers.dto.profesor;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Generated
public class ProfesorSimpleOutputDto {
    private int id_profesor;
    private int id_persona;
    private String comments;
    private String branch;
}
