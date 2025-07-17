package br.ufrn.myway.Model.Mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.myway.Model.DTO.GoalDTO;
import br.ufrn.myway.Model.DTO.Response.ResponseGenerateGoalDTO;
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

    public List<AbstractGoal> toEntityListFromResponse(List<ResponseGenerateGoalDTO> responseGoalDTOs) {
    // Convert each ResponseGenerateGoalDTO to GoalDTO, then reuse the existing method
    //  String name,
    //     String description,
    //     Long roadmapIndex,
    //     List<ResponseGenerateStudyTopicDTO> studyTopics,
    //     String tipo //pode ser daily ou normal

    // Long id,
    //     String type,
    //     String description,
    //     List<StudyTopicDTO> studyTopics,
    //     String status,
    //     LocalDateTime resetTime
    List<GoalDTO> goalDTOs = responseGoalDTOs.stream()
        .map(responseDTO -> new GoalDTO(
            1L,
            responseDTO.tipo(),
            responseDTO.description(),
            studyTopicMapper.toDTOListFromResponse(responseDTO.studyTopics()),
            "PENDING",
            LocalDateTime.now()
        ))
        .toList();
    return toEntityList(goalDTOs);
}

    public List<GoalDTO> toDTOList(List<AbstractGoal> goals) {
        return goals.stream().map(this::toDTO).toList();
    }

    // Add this overloaded method to handle ResponseGenerateGoalDTO
    // public List<ResponseGenerateGoalDTO> toEntityList(List<ResponseGenerateGoalDTO> dtos) {
    //     if (dtos == null) {
    //         return null;
    //     }
    //     return dtos.stream().map(this::toEntity).toList();
    // }

// You must also have a method to convert a single ResponseGenerateGoalDTO to Goal
    // public AbstractGoal toEntity(ResponseGenerateGoalDTO dto) {
    //     // Implement the mapping logic here
    //     // Example:
    //     AbstractGoal goal = new AbstractGoal();
    //     goal.setName(dto.getName());
    //     goal.setDescription(dto.getDescription());
    //     // Set other fields as needed
    //     return goal;
    // }
}
