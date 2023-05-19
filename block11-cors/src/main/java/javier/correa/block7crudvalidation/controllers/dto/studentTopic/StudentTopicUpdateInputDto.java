package javier.correa.block7crudvalidation.controllers.dto.studentTopic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentTopicUpdateInputDto {

    List<Integer> id_study = new ArrayList<>();

}
