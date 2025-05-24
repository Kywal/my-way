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

    private int size;

    public Goal(String nameGoal, Roadmap roadMap, int size, List<StudyTopic> exercices) {
        this.name = nameGoal;
        this.roadMap = roadMap;
        this.size = size;
        this.exercises = exercices;
    }

    public Goal() {

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

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

    
}
