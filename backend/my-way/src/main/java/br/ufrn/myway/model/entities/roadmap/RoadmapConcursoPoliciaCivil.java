package br.ufrn.myway.model.entities.roadmap;

import jakarta.persistence.Entity;

@Entity
public class RoadmapConcursoPoliciaCivil extends RoadmapBase {

    public RoadmapConcursoPoliciaCivil() {
        super();
    }

    @Override
    public String getDescription() {
        return "Roadmap para o concurso da Policia Civil";
    }

}
