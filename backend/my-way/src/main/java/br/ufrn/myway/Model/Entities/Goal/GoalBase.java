package br.ufrn.myway.Model.Entities.Goal;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.myway.Model.Entities.AbstractModel;
import br.ufrn.myway.Model.Entities.Roadmap.RoadmapBase;
import br.ufrn.myway.Model.Entities.StudyTopic; 
import br.ufrn.myway.Model.Enums.GoalStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class GoalBase extends AbstractModel implements AbstractGoal {

    @Column(nullable = false)
    private String name;

    private String description;
    private Long roadmapIndex;
    private GoalStatus status;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL)
    private List<StudyTopic> studyTopics = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_roadmap")
    private RoadmapBase roadmap;

    public GoalBase() {
    }

    public GoalBase(String name, String description, Long roadmapIndex) {
        this.name = name;
        this.description = description;
        this.roadmapIndex = roadmapIndex;
    }

    public RoadmapBase getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(RoadmapBase roadMap) {
        this.roadmap = roadMap;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
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

    @Override
    public Long getRoadmapIndex() {
        return roadmapIndex;
    }

    public void setRoadmapIndex(Long roadmapIndex) {
        this.roadmapIndex = roadmapIndex;
    }

    @Override
    public GoalStatus getStatus() {
        return status;
    }

    public void setStatus(GoalStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder goal
                = new StringBuilder(
                        roadmapIndex + " - " + name + "\n"
                        + "\tDescrição: " + description + "\n"
                        + "\tStatus: " + status + "\n"
                        + "\tTópicos de estudo:\n"
                );

        for (StudyTopic st : studyTopics) {
            goal.append(st);
        }

        return goal.append("\n").toString();
    }
}
