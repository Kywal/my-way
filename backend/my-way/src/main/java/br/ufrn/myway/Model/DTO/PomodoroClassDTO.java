package DTO;

import Entities.PomodoroStatus;

import java.time.LocalDateTime;

public record PomodoroClassDTO(int upTime, int downTime, int longDownTime,
                               int cyclesBeforeLongBreak, LocalDateTime startTime,
                               PomodoroStatus pomodoroStatus, int completedPomodoros) {
}
