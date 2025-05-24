package br.ufrn.myway.Model.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Goal extends AbstractModel {

    private String name;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<StudyTopic> exercises = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_roadmap")
    private Roadmap roadMap;

    private int roadmapIndex;

    public Goal(String nameGoal, Roadmap roadMap, List<StudyTopic> exercices) {
        this.name = nameGoal;
        this.roadMap = roadMap;
        this.exercises = exercices;
    }

    public Goal() {}

    public Roadmap getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(Roadmap roadMap) {
        this.roadMap = roadMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudyTopic> getExercises() {
        return exercises;
    }

    public void setExercises(List<StudyTopic> exercises) {
        this.exercises = exercises;
    }

    public int getRoadmapIndex() {
        return roadmapIndex;
    }

    public void setRoadmapIndex(int roadmapIndex) {
        this.roadmapIndex = roadmapIndex;
    }
}
