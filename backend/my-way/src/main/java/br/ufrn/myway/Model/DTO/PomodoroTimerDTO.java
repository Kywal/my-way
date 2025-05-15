package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Enums.PomodoroStatus;

import java.time.LocalDateTime;

public record PomodoroTimerDTO (int upTime, int downTime, int longDownTime,
                               int cyclesBeforeLongBreak, LocalDateTime startTime,
                               PomodoroStatus pomodoroStatus, int completedPomodoros){
}
