package br.ufrn.myway.model.entities.Goal;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.myway.model.entities.AbstractModel;
import br.ufrn.myway.model.entities.roadmap.RoadmapBase;
import br.ufrn.myway.model.entities.StudyTopic;
import br.ufrn.myway.model.enums.GoalStatus;
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

    @Override
    public RoadmapBase getRoadmap() {
        return roadmap;
    }

    @Override
    public void setRoadmap(RoadmapBase roadMap) {
        this.roadmap = roadMap;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<StudyTopic> getStudyTopics() {
        return studyTopics;
    }

    @Override
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

    @Override
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
