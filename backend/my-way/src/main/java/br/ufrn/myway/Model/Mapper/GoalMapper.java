package br.ufrn.myway.Model.Mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.Entities.Goal.AbstractGoal; 
import br.ufrn.myway.Model.Entities.Goal.DailyGoal;
import br.ufrn.myway.Model.Entities.Goal.GoalBase;
import br.ufrn.myway.Model.Entities.Goal.StaticGoal;
import br.ufrn.myway.Model.Enums.GoalStatus; 

@Mapper(componentModel = "spring", uses = {StudyTopicMapper.class})
public abstract class GoalMapper {

    @Autowired
    protected StudyTopicMapper studyTopicMapper;

    public AbstractGoal toEntity(GoalDTO dto) {
        GoalBase goal;
        if ("DAILY".equalsIgnoreCase(dto.type())) {
            DailyGoal daily = new DailyGoal();
            daily.setResetTime(dto.resetTime());
            goal = daily;
        } else {
            goal = new StaticGoal();
        }

        goal.setDescription(dto.description());
        goal.setStatus(GoalStatus.valueOf(dto.status()));
        goal.setStudyTopics(studyTopicMapper.toEntityList(dto.studyTopics()));

        return goal;
    }

    public GoalDTO toDTO(AbstractGoal goal) {
        GoalBase base = (GoalBase) goal;

        String type = (goal instanceof DailyGoal) ? "DAILY" : "STATIC";
        LocalDateTime resetTime = (goal instanceof DailyGoal d) ? d.getResetTime() : null;

        return new GoalDTO(
            base.getId(),
            type,
            base.getDescription(),
            studyTopicMapper.toDTOList(base.getStudyTopics()),
            base.getStatus().name(),
            resetTime
        );
    }

    public List<AbstractGoal> toEntityList(List<GoalDTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    public List<GoalDTO> toDTOList(List<AbstractGoal> goals) {
        return goals.stream().map(this::toDTO).toList();
    }
}
