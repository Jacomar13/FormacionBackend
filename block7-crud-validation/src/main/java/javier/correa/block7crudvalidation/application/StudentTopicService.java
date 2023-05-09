package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorInputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.profesor.ProfesorWithStudentOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;

public interface StudentTopicService {

    StudentTopicOutputDto addTopic(StudentTopicInputDto studentTopicInputDto) throws Exception;

    Iterable<StudentTopicOutputDto> getAllTopics(int pageNumber, int pageSize);

    Object getListOfTopicByStudent(int id_student);

    void addTopicToStudent(int id_student, int id_topic);

}
