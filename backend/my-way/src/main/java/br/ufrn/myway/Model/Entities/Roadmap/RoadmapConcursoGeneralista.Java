package br.ufrn.myway.Model.Entities.Roadmap;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roadmap_concurso_generalista")
public class RoadmapConcursoGeneralista extends RoadmapBase {

    public RoadmapConcursoGeneralista() {
        super();
    }

    @Override
    public String getDescription() {
        return "Roadmap para concursos gerais";
    }

}
