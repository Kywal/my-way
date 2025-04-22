package br.ufrn.myway.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;

import java.util.List;

@Entity
@Table(name = "tb_roadmap")
public class Roadmap extends AbstractModel {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne
    @JoinColumn(name ="id_final_goal")
    private Goal finalGoal;

    @OneToMany(mappedBy = "roadmap")
    @OrderColumn(name = "path_goals_order")
    private List<Goal> pathGoals;

    public Roadmap() {
    }

    public Roadmap(String name, String description, User user, Goal finalGoal, List<Goal> pathGoals) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.finalGoal = finalGoal;
        this.pathGoals = pathGoals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goal getFinalGoal() {
        return finalGoal;
    }

    public void setFinalGoal(Goal finalGoal) {
        this.finalGoal = finalGoal;
    }

    public List<Goal> getPathGoals() {
        return pathGoals;
    }

    public void setPathGoals(List<Goal> pathGoals) {
        this.pathGoals = pathGoals;
    }
}
