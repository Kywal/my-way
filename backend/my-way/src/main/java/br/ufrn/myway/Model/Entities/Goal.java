package br.ufrn.myway.Model.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Goal extends AbstractModel {

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<StudyTopic> exercises = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_roadmap")
    private Roadmap roadmap;

    @Column(unique = true)
    private Long roadmapIndex;

    public Goal(String nameGoal, Roadmap roadmap, List<StudyTopic> exercices) {
        this.name = nameGoal;
        this.roadmap = roadmap;
        this.exercises = exercices;
    }

    public Goal() {}

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

    public List<StudyTopic> getExercises() {
        return exercises;
    }

    public void setExercises(List<StudyTopic> exercises) {
        this.exercises = exercises;
    }

    public Long getRoadmapIndex() {
        return roadmapIndex;
    }

    public void setRoadmapIndex(Long roadmapIndex) {
        this.roadmapIndex = roadmapIndex;
    }
}
