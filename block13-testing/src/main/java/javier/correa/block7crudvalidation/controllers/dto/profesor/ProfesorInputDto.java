package javier.correa.block7crudvalidation.controllers.dto.profesor;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfesorInputDto {
    private int id_profesor;
    private int id_persona;
    private String comments;
    private String branch;

}
