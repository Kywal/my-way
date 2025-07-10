package br.ufrn.myway.Model.Entities.Roadmap;

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
