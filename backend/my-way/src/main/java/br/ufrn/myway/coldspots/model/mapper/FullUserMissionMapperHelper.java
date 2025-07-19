package br.ufrn.myway.coldspots.model.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.ufrn.myway.coldspots.model.DTO.MissionDTO;
import br.ufrn.myway.coldspots.model.DTO.SimpleUserDTO;
import br.ufrn.myway.coldspots.Mission.model.Mission;
import br.ufrn.myway.coldspots.model.entities.User;

public class FullUserMissionMapperHelper {

    public static String toString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public static MissionDTO toMissionDTO(Mission mission) {
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

    public static SimpleUserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return new SimpleUserDTO(
                user.getEmail(),
                user.getTokens()
        );
    }
}