package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Enums.PomodoroStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PomodoroTimer extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int upTime = 25;
    private int downTime = 5;
    private int longDownTime = 15;
    private int cyclesBeforeLongBreak = 4;

    private LocalDateTime startTime;

    @Enumerated(EnumType.STRING)
    private PomodoroStatus pomodoroStatus;

    private int completedPomodoros = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUpTime() {
        return upTime;
    }

    public void setUpTime(int upTime) {
        this.upTime = upTime;
    }

    public int getDownTime() {
        return downTime;
    }

    public void setDownTime(int downTime) {
        this.downTime = downTime;
    }

    public int getLongDownTime() {
        return longDownTime;
    }

    public void setLongDownTime(int longDownTime) {
        this.longDownTime = longDownTime;
    }

    public int getCyclesBeforeLongBreak() {
        return cyclesBeforeLongBreak;
    }

    public void setCyclesBeforeLongBreak(int cyclesBeforeLongBreak) {
        this.cyclesBeforeLongBreak = cyclesBeforeLongBreak;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public PomodoroStatus getPomodoroStatus() {
        return pomodoroStatus;
    }

    public void setPomodoroStatus(PomodoroStatus pomodoroStatus) {
        this.pomodoroStatus = pomodoroStatus;
    }

    public int getCompletedPomodoros() {
        return completedPomodoros;
    }

    public void setCompletedPomodoros(int completedPomodoros) {
        this.completedPomodoros = completedPomodoros;
    }

    public PomodoroTimer(Long id, int upTime, int downTime, int longDownTime,
                         int cyclesBeforeLongBreak, LocalDateTime startTime,
                         PomodoroStatus pomodoroStatus, int completedPomodoros) {
        this.id = id;
        this.upTime = upTime;
        this.downTime = downTime;
        this.longDownTime = longDownTime;
        this.cyclesBeforeLongBreak = cyclesBeforeLongBreak;
        this.startTime = startTime;
        this.pomodoroStatus = pomodoroStatus;
        this.completedPomodoros = completedPomodoros;
    }
}
