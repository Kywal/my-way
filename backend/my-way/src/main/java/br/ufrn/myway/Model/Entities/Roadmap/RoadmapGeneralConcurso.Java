package br.ufrn.myway.Model.Entities.Roadmap;

import jakarta.persistence.Entity;

@Entity
public class RoadmapGeneralConcurso extends RoadmapBase {

    public RoadmapGeneralConcurso() {
        super();
    }

    public String getTeste() {
        return "teste";
    }
}