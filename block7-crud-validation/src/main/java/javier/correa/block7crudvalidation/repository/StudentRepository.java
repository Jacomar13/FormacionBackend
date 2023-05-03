package javier.correa.block7crudvalidation.repository;


import javier.correa.block7crudvalidation.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentRepository extends JpaRepository<Student, Integer> {

}
