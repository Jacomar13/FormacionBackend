package javier.correa.block7crudvalidation.repository;

import javier.correa.block7crudvalidation.domain.StudentTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentTopicRepository extends JpaRepository<StudentTopic, Integer> {
}
