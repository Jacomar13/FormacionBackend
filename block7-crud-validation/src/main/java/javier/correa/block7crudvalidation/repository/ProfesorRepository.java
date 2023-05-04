package javier.correa.block7crudvalidation.repository;

import javier.correa.block7crudvalidation.domain.Profesor;

import javier.correa.block7crudvalidation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    @Query(value = "select * from profesor where id_persona = ?1", nativeQuery = true)
    Profesor findByIdPersona(int id);
}
