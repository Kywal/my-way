package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Enums.RoadMapStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Roadmap extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(nullable = false)
    private String mainGoal;

    private String description;

    @OrderBy("roadmapIndex")
    @OneToMany(mappedBy = "roadMap", cascade = CascadeType.ALL) // Must match Goal's property name
    private List<Goal> goals = new ArrayList<>();

    private RoadMapStatus status;

    public Roadmap(User user, String mainGoal, List<Goal> goals) {
        this.user = user;
        this.mainGoal = mainGoal;
        this.goals = goals;
    }

    public Roadmap() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(String name) {
        this.mainGoal = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> listGoals) {
        this.goals = listGoals;
    }

    public RoadMapStatus getStatus() {
        return status;
    }

    public void setStatus(RoadMapStatus status) {
        this.status = status;
    }

    
}
