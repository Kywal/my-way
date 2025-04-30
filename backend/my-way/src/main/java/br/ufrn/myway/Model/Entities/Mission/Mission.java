package br.ufrn.myway.Model.Entities.Mission;

import java.time.LocalDateTime;

import br.ufrn.myway.Model.Entities.AbstractModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Table(name = "tb_mission")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mission extends AbstractModel {

    private String title;

    private String description;

    private int rewardPoints;

    private boolean completed = false;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;

    }
}
