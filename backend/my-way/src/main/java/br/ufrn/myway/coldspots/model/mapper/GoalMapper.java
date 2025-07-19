package br.ufrn.myway.coldspots.model.mapper;

import br.ufrn.myway.coldspots.model.DTO.Response.ResponseGenerateGoalDTO;
import br.ufrn.myway.coldspots.model.entities.Goal.GoalBase;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {StudyTopicMapper.class})
public abstract class GoalMapper {

    @Autowired
    protected StudyTopicMapper studyTopicMapper;

    public abstract GoalBase toEntity(ResponseGenerateGoalDTO goalDTO);
}