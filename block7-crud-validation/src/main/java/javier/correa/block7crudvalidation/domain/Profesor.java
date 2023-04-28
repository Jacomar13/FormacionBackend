package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*@Data
@Entity
@NoArgsConstructor*/
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id_profesor;

    @OneToOne
    @JoinColumn(name = "id_persona")
    Persona persona;

   private String comments;

   private String branch;

}
