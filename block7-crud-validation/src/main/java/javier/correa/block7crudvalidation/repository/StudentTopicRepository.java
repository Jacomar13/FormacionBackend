package javier.correa.block7crudvalidation.repository;


import javier.correa.block7crudvalidation.domain.StudentTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface StudentTopicRepository extends JpaRepository<StudentTopic, Integer> {
    @Query(value = "select * from estudios where id_student = :id_student", nativeQuery = true)
    Set<StudentTopic> findByIdStudent(int id_student);
}
