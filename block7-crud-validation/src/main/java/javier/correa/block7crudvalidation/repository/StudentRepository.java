package javier.correa.block7crudvalidation.repository;

import javier.correa.block7crudvalidation.domain.Persona;
import javier.correa.block7crudvalidation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
