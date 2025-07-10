package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Entities.Goal.AbstractGoal; 
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
    private AbstractGoal goal;

    private StudyTopicStatus status;

    // Contrusctors

    public StudyTopic() {}

    public StudyTopic(String name, String description, AbstractGoal goal) {
        this.name = name;
        this.description = description;
        this.goal = goal;
    }
    // Utils

    @Override
    public String toString() {
        return "\t - " + name + "\n" +
                "\t\t" + description + "\n" +
                "\t\tStatus: " + status + "\n";
    }

    // Getters and setters

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

    public AbstractGoal getGoal() {
        return goal;
    }

    public void setGoal(AbstractGoal goal) {
        this.goal = goal;
    }

    public StudyTopicStatus getStatus() {
        return status;
    }

    public void setStatus(StudyTopicStatus status) {
        this.status = status;
    }

}
