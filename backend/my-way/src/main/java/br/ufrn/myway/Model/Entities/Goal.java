package br.ufrn.myway.Model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Goal extends AbstractModel {

    private String nameGoal;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<StudyTopic> exercises = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_roadmap")
    private RoadMap roadMap;

    private int size;

    public Goal(String nameGoal, RoadMap roadMap, int size) {
        this.nameGoal = nameGoal;
        this.roadMap = roadMap;
        this.size = size;
    }

    public Goal() {

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public RoadMap getRoadMap() {
        return roadMap;
    }

    public String getNameGoal() {
        return nameGoal;
    }

    public void setNameGoal(String nameGoal) {
        this.nameGoal = nameGoal;
    }
}
