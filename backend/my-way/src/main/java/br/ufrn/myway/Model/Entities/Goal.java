package br.ufrn.myway.Model.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_goal")
public class Goal extends AbstractModel {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_roadmap")
    private Roadmap roadmap;

    public Goal() {
    }

    public Goal(String name, String description, Roadmap roadmap) {
        this.name = name;
        this.description = description;
        this.roadmap = roadmap;
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

    public Roadmap getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(Roadmap roadmap) {
        this.roadmap = roadmap;
    }
}
