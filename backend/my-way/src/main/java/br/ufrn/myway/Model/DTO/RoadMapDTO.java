package br.ufrn.myway.Model.DTO;
import java.time.LocalDate;
import java.util.List;

public record RoadMapDTO (Long id, LocalDate createdAt, String name, UserDTO user, List<GoalDTO> listGoals){
}
