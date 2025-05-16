package br.ufrn.myway.Model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_goal")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGoal;

    private String nameGoal;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<StudyTopic> listTopics = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private RoadMap roadMap;

    public Goal(Long idGoal, String nameGoal, List<StudyTopic> listTopics) {
        this.idGoal = idGoal;
        this.nameGoal = nameGoal;
        this.listTopics = listTopics;
    }

    public RoadMap getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public Goal() {
    }

    public Long getIdGoal() {
        return idGoal;
    }

    public void setIdGoal(Long idGoal) {
        this.idGoal = idGoal;
    }

    public String getNameGoal() {
        return nameGoal;
    }

    public void setNameGoal(String nameGoal) {
        this.nameGoal = nameGoal;
    }

    public List<StudyTopic> getListTopics() {
        return listTopics;
    }

    public void setListTopics(List<StudyTopic> listTopics) {
        this.listTopics = listTopics;
    }
    public void addStudyTopic(StudyTopic studyTopic){
        this.listTopics.add(studyTopic);
        studyTopic.setGoal(this);
    }
}
