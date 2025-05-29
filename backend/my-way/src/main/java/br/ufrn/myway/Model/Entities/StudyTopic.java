package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Enums.StudyTopicStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudyTopic extends AbstractModel {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_goal")
    private Goal goal;

    private StudyTopicStatus status;

    public StudyTopic(String name, String description, Goal goal) {
        this.name = name;
        this.description = description;
        this.goal = goal;
    }

    public StudyTopic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public StudyTopicStatus getStatus() {
        return status;
    }

    public void setStatus(StudyTopicStatus status) {
        this.status = status;
    }

}
