package br.ufrn.myway.model.entities.roadmap;

import jakarta.persistence.Entity;

@Entity
public class RoadmapConcursoProfessor extends RoadmapBase {

    public RoadmapConcursoProfessor() {
        super();
    }

    @Override
    public String getDescription() {
        return "Roadmap para o concurso de Professor";
    }

}
