package br.ufrn.myway.coldspots.model.entities.Goal;

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
