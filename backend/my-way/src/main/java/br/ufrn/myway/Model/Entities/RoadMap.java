package br.ufrn.myway.Model.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roadmap")
public class RoadMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoadMap;

    private String tittleRoadMap;

    private String descriptionRoadMap;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    //@JsonIgnore
    private String userAssociatedWithRoadMap;

    @OneToMany(mappedBy = "roadMap", cascade = CascadeType.ALL) // Must match Goal's property name
    private List<Goal> listGoals = new ArrayList<>();

    public RoadMap(Long idRoadMap, String tittleRoadMap, String descriptionRoadMap, String userAssociatedWithRoadMap, List<Goal> listGoals) {
        this.idRoadMap = idRoadMap;
        this.tittleRoadMap = tittleRoadMap;
        this.descriptionRoadMap = descriptionRoadMap;
        this.userAssociatedWithRoadMap = userAssociatedWithRoadMap;
        this.listGoals = listGoals;
    }

    public RoadMap() {
    }

    public String getTittleRoadMap() {
        return tittleRoadMap;
    }

    public void setTittleRoadMap(String tittleRoadMap) {
        this.tittleRoadMap = tittleRoadMap;
    }

    public String getDescriptionRoadMap() {
        return descriptionRoadMap;
    }

    public void setDescriptionRoadMap(String descriptionRoadMap) {
        this.descriptionRoadMap = descriptionRoadMap;
    }

    public String getUserAssociatedWithRoadMap() {
        return userAssociatedWithRoadMap;
    }

    public void setUserAssociatedWithRoadMap(String userAssociatedWithRoadMap) {
        this.userAssociatedWithRoadMap = userAssociatedWithRoadMap;
    }

    public List<Goal> getListGoals() {
        return listGoals;
    }

    public void setListGoals(List<Goal> listGoals) {
        this.listGoals = listGoals;
    }
    public void addGoal(Goal goal){
        this.listGoals.add(goal);
        goal.setRoadMap(this);
    }
}
