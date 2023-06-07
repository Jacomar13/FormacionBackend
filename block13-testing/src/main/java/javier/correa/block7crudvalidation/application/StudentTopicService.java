package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.student.StudentWithTopicsOutputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;

public interface StudentTopicService {

    StudentTopicOutputDto addTopic(StudentTopicInputDto studentTopicInputDto) throws Exception;

    Iterable<StudentTopicOutputDto> getAllTopics(int pageNumber, int pageSize);

    StudentWithTopicsOutputDto getListOfTopicByStudent(int idStudent);

    void deleteTopicById(int id);

    StudentTopicOutputDto updateTopic(int id, StudentTopicInputDto studentTopicInputDto);
    StudentTopicOutputDto getTopicById(Integer id);

}
