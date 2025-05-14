package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Enums.MissionFrequency;
import br.ufrn.myway.Model.Enums.MissionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "tb_mission")
@Entity
public class Mission extends AbstractModel {

    private String title;

    private String description;

    private int rewardPoints;

    @Enumerated(EnumType.STRING)
    private MissionFrequency frequency;

    @Enumerated(EnumType.STRING)
    private MissionType type;

    private Integer quantityGoal;

    private Integer timeInMinutes;

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

    public Integer getQuantityGoal() {
        return quantityGoal;
    }

    public void setQuantityGoal(Integer quantityGoal) {
        this.quantityGoal = quantityGoal;
    }

    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public MissionFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(MissionFrequency frequency) {
        this.frequency = frequency;
    }

    public MissionType getType() {
        return type;
    }

    public void setType(MissionType type) {
        this.type = type;
    }

}
