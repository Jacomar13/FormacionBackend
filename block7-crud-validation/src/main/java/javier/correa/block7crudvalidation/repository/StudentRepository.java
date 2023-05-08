package javier.correa.block7crudvalidation.repository;


import javier.correa.block7crudvalidation.domain.Profesor;
import javier.correa.block7crudvalidation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select * from estudiantes where id_persona = ?1", nativeQuery = true)
    Student findByIdPersona(int id);

    @Query(value = "select * from estudiantes where id_profesor = :id_profesor", nativeQuery = true)
    Set<Student> findByIdProfesor(int id_profesor);
}
