package br.ufrn.myway.coldspots.model.DTO;

public record FullUserMissionDTO(SimpleUserDTO user, MissionDTO mission, boolean completed, String startDate, String endDate) {
}
