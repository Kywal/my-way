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
public class RoadMap extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String name;

    @OneToMany(mappedBy = "roadMap", cascade = CascadeType.ALL) // Must match Goal's property name
    private List<Goal> listGoals = new ArrayList<>();

    private RoadMapStatus status;

    public RoadMap(User user, String name, List<Goal> listGoals) {
        this.user = user;
        this.name = name;
        this.listGoals = listGoals;
    }

    public RoadMap() {}

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

    public List<Goal> getListGoals() {
        return listGoals;
    }

    public void setListGoals(List<Goal> listGoals) {
        this.listGoals = listGoals;
    }

    public RoadMapStatus getStatus() {
        return status;
    }

    public void setStatus(RoadMapStatus status) {
        this.status = status;
    }

    
}
