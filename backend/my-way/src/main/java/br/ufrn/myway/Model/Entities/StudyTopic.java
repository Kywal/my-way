package br.ufrn.myway.Model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class StudyTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudyTopic;

    private String nameTopic;

    private String descriptionTopic;
    //@ManyToOne
    //@JoinColumn(name = "user_id")
    @ManyToOne
    @JsonIgnore
    private Goal goal;

    private boolean isDone;

    public StudyTopic(Long idStudyTopic, String nameTopic, String descriptionTopic, boolean isDone) {
        this.idStudyTopic = idStudyTopic;
        this.nameTopic = nameTopic;
        this.descriptionTopic = descriptionTopic;
        this.isDone = isDone;
    }
    public StudyTopic() {
    }

    public Long getIdStudyTopic() {
        return idStudyTopic;
    }

    public void setIdStudyTopic(Long idStudyTopic) {
        this.idStudyTopic = idStudyTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getDescriptionTopic() {
        return descriptionTopic;
    }

    public void setDescriptionTopic(String descriptionTopic) {
        this.descriptionTopic = descriptionTopic;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
