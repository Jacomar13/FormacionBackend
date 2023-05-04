package javier.correa.block7crudvalidation.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ProfesorOutputDto {
    private int id_profesor;
    private String comments;
    private String branch;
    private int id_persona;

    private String usuario;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private boolean active;

    private Date created_date;

    private String imagen_url;

    private Date termination_date;
}
