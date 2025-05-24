package br.ufrn.myway.Model.Mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.ufrn.myway.Model.DTO.FullUserMissionDTO;
import br.ufrn.myway.Model.DTO.MissionDTO;
import br.ufrn.myway.Model.DTO.SimpleUserDTO; 
import br.ufrn.myway.Model.Entities.Mission;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Entities.UserMission;

@Mapper(componentModel = "spring")
public interface FullUserMissionMapper {

    UserMission toEntity(FullUserMissionDTO dto);

    @Mapping(target = "startDate", expression = "java(toString(userMission.getStartDate()))")
    @Mapping(target = "endDate", expression = "java(toString(userMission.getEndDate()))")
    @Mapping(target = "mission", expression = "java(toMissionDTO(userMission.getMission()))")
    @Mapping(target = "user", expression = "java(toUserDTO(userMission.getUser()))")
    FullUserMissionDTO toDto(UserMission userMission);

    default String toString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    default MissionDTO toMissionDTO(Mission mission) {
        if (mission == null) {
            return null;
        }
        return new MissionDTO(
                mission.getTitle(),
                mission.getDescription(),
                mission.getRewardPoints(),
                mission.getTimeInMinutes(),
                mission.getQuantityGoal(),
                mission.getType().toString(),
                mission.getFrequency().toString()
        );
    }

    default SimpleUserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return new SimpleUserDTO(
                user.getEmail(),
                user.getTokens()
        );
    }
}
