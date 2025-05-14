package br.ufrn.myway.Model.DTO;

public record MissionDTO(
        String title,
        String description,
        int rewardPoints,
        Integer quantityGoal,
        Integer timeInMinutes
        ) {

}
