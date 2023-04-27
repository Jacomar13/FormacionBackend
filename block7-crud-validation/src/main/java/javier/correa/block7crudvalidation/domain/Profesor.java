package javier.correa.block7crudvalidation.domain;

import jakarta.persistence.*;

import java.util.List;
public class Profesor {

   private int id_profesor;

   private int id_persona;

   private String comments;

   private enum branch{
       BACK,
       FRONT,
       DEVOPS,
       UNASIGNED
   }

}
