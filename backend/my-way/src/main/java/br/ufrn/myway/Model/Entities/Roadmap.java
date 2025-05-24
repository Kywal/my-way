package br.ufrn.myway.Model.Entities;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.myway.Model.Enums.RoadMapStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Roadmap extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String name;

    @OneToMany(mappedBy = "roadMap", cascade = CascadeType.ALL) // Must match Goal's property name
    private List<Goal> goals = new ArrayList<>();

    private RoadMapStatus status;

    public Roadmap(User user, String name, List<Goal> goals) {
        this.user = user;
        this.name = name;
        this.goals = goals;
    }

    public Roadmap() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
