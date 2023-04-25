package javier.correa.block7crudvalidation.controllers.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
@Data
public class PersonaInputDto {
    private int id_persona;

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private boolean active;

    private Date created_date;

    private String imagen_url;

    private String termination_date;
}
