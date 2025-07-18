package br.ufrn.myway.Model.Entities.Roadmap;

import jakarta.persistence.Entity;

@Entity
public class RoadmapConcursoGeneralista extends RoadmapBase {

    public RoadmapConcursoGeneralista() {
        super();
    }

    @Override
    public String getDescription() {
        return "Roadmap para concursos gerais";
    }

}
