package br.ufrn.myway.coldspots.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.ufrn.myway.coldspots.model.DTO.FullUserMissionDTO;
import br.ufrn.myway.coldspots.Mission.model.UserMission;

@Mapper(componentModel = "spring", uses = FullUserMissionMapperHelper.class)
public interface FullUserMissionMapper {

    UserMission toEntity(FullUserMissionDTO dto);

    @Mapping(target = "startDate", expression = "java(FullUserMissionMapperHelper.toString(userMission.getStartDate()))")
    @Mapping(target = "endDate", expression = "java(FullUserMissionMapperHelper.toString(userMission.getEndDate()))")
    @Mapping(target = "mission", expression = "java(FullUserMissionMapperHelper.toMissionDTO(userMission.getMission()))")
    @Mapping(target = "user", expression = "java(FullUserMissionMapperHelper.toUserDTO(userMission.getUser()))")
    FullUserMissionDTO toDto(UserMission userMission);
}
