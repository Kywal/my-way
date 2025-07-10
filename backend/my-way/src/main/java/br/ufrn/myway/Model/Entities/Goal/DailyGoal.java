package br.ufrn.myway.Model.Entities.Goal;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class DailyGoal extends GoalBase {

    private LocalDateTime resetTime;

    public DailyGoal() {
        super();
    }

    public DailyGoal(String name, String description, Long roadmapIndex, LocalDateTime resetTime) {
        super(name, description, roadmapIndex);
        this.resetTime = resetTime;
    }

    public LocalDateTime getResetTime() {
        return resetTime;
    }

    public void setResetTime(LocalDateTime resetTime) {
        this.resetTime = resetTime;
    }
}
