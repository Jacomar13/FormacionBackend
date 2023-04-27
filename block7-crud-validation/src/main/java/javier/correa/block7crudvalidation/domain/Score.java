package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private int id_score;

    private int id_student;

    private int id_registry_type;

    private float note;

    private enum branch {
        BACK,
        FRONT,
        DEVOPS,
        UNASIGNED
    };

    private int id_topic;

    private String comment;

    private Date creationDate;
}
