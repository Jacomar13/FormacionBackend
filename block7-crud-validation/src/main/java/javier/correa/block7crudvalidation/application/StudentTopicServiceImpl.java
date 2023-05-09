package javier.correa.block7crudvalidation.application;

import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicInputDto;
import javier.correa.block7crudvalidation.controllers.dto.studentTopic.StudentTopicOutputDto;
import org.springframework.stereotype.Service;

@Service
public class StudentTopicServiceImpl implements StudentTopicService{
    @Override
    public StudentTopicOutputDto addTopic(StudentTopicInputDto studentTopicInputDto) throws Exception {
        return null;
    }

    @Override
    public Iterable<StudentTopicOutputDto> getAllTopics(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Object getListOfTopicByStudent(int id_student) {
        return null;
    }

    @Override
    public void addTopicToStudent(int id_student, int id_topic) {

    }
}
