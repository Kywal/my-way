package br.ufrn.myway.model.entities.Goal;

import java.util.List;

import br.ufrn.myway.model.entities.roadmap.RoadmapBase;
import br.ufrn.myway.model.entities.StudyTopic;
import br.ufrn.myway.model.Enums.GoalStatus;

public interface AbstractGoal {

    public Long getId();
    
    public String getName(); 

    public String getDescription(); 

    public Long getRoadmapIndex(); 

    public GoalStatus getStatus(); 

    public List<StudyTopic> getStudyTopics();
    
    public RoadmapBase getRoadmap();
    
    public void setStatus(GoalStatus status);

    public void setName(String name);

    public void setDescription(String description);

    public void setRoadmapIndex(Long roadmapIndex);

    public void setStudyTopics(List<StudyTopic> exercises);
    
    public void setRoadmap(RoadmapBase roadMap);

    public void setId(Long id);
}
