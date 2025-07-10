package br.ufrn.myway.Model.Entities.Goal;

import br.ufrn.myway.Model.Enums.GoalStatus;

public interface AbstractGoal {
    public String getName(); 

    public String getDescription(); 

    public Long getRoadmapIndex(); 

    public GoalStatus getStatus(); 
}
