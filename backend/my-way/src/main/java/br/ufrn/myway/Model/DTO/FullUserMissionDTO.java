package br.ufrn.myway.Model.DTO;

public record FullUserMissionDTO(SimpleUserDTO user, MissionDTO mission, boolean completed, String startDate, String endDate) {
}
