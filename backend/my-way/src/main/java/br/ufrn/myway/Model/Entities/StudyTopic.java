package br.ufrn.myway.Model.Entities;

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

    private boolean isDone;

    // Contrusctors

    public StudyTopic() {}

    public StudyTopic(String name, String description, Goal goal, boolean isDone) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.isDone = isDone;
    }

    // Utils

    @Override
    public String toString() {
        return "\t - " + name + "\n" +
                "\t\t" + description + "\n" +
                "\t\tJá foi estudado?" + isDone + "\n";
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
}
