package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.PomodoroTimerDTO;
import br.ufrn.myway.Model.Entities.PomodoroTimer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PomodoroTimerMapper {
    PomodoroTimer toEntity(PomodoroTimerDTO pomodoroDto);
    PomodoroTimerDTO toDto(PomodoroTimer pomodoroEntity);
}
