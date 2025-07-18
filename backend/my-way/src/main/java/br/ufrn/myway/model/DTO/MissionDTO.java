package br.ufrn.myway.model.DTO;

public record MissionDTO(
        String title,
        String description,
        int rewardPoints,
        Integer quantityGoal,
        Integer timeInMinutes,
        String frequency,
        String type
        ) {

}
