package br.ufrn.myway.Model.DTO.Response;

import java.time.LocalDate;

public record ResponseGoalDTO(Long id, LocalDate createdAt, String name, String description, Long roadmapId, int roadmapIndex) {
}
