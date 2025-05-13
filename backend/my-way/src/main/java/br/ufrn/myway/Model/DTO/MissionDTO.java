package br.ufrn.myway.Model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import br.ufrn.myway.Model.Entities.User;

public record MissionDTO(
        String title,
        String description,
        int rewardPoints,
        LocalDateTime startDate,
        LocalDateTime endDate,
        boolean completed,
        Integer quantityGoal,
        Integer timeInMinutes,
        List<User> users
        ) {

}
