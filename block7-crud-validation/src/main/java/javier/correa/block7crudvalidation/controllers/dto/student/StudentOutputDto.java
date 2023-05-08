package javier.correa.block7crudvalidation.controllers.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class StudentOutputDto {
    private int id_student;
    private Integer num_hours_week;
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
