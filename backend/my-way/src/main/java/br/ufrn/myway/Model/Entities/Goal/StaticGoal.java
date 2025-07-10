package br.ufrn.myway.Model.Entities.Goal;

import jakarta.persistence.Entity;

@Entity
public class StaticGoal extends GoalBase {
    
    public StaticGoal() {
        super();
    }

    public StaticGoal(String name, String description, Long roadmapIndex) {
        super(name, description, roadmapIndex);
    }
}
