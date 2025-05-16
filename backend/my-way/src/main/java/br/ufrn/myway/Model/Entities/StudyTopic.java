package br.ufrn.myway.Model.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudyTopic extends AbstractModel {

    private String nome;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_goal")
    private Goal goal;

    private boolean isDone = false ;

    public StudyTopic(String nome, String description, Goal goal, boolean isDone) {
        this.nome = nome;
        this.description = description;
        this.goal = goal;
        this.isDone = isDone;
    }

    public StudyTopic() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setDone(boolean done) {
        isDone = done;
    }
}
