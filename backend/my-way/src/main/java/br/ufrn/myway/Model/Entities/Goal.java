package br.ufrn.myway.Model.Entities;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.myway.Model.Enums.GoalStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Goal extends AbstractModel {

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<StudyTopic> studyTopics = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_roadmap")
    private Roadmap roadmap;

    private Long roadmapIndex;

    private GoalStatus status;

    // Constructors

    public Goal() {}

    public Goal(String nameGoal, Roadmap roadmap, List<StudyTopic> exercices) {
        this.name = nameGoal;
        this.roadmap = roadmap;
        this.studyTopics = exercices;
    }

    // Utils

    @Override
    public String toString() {
        StringBuilder goal =
                new StringBuilder(
                        roadmapIndex + " - " + name + "\n" +
                            "\tDescrição: " + description + "\n" +
                            "\tStatus: " + status + "\n" +
                            "\tTópicos de estudo:\n"
                );

        for (StudyTopic st : studyTopics) {
            goal.append(st);
        }

        return goal.append("\n").toString();
    }


    // Getters and setters

    public Roadmap getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(Roadmap roadMap) {
        this.roadmap = roadMap;
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

    public List<StudyTopic> getStudyTopics() {
        return studyTopics;
    }

    public void setStudyTopics(List<StudyTopic> exercises) {
        this.studyTopics = exercises;
    }

    public Long getRoadmapIndex() {
        return roadmapIndex;
    }

    public void setRoadmapIndex(Long roadmapIndex) {
        this.roadmapIndex = roadmapIndex;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public void setStatus(GoalStatus status) {
        this.status = status;
    }

}
