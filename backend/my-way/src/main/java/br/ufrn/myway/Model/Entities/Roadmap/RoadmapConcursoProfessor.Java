package br.ufrn.myway.Model.Entities.Roadmap;

import jakarta.persistence.Entity;

@Entity
public class RoadmapConcursoProfessor extends RoadmapBase {

    public RoadmapConcursoProfessor() {
        super();
    }

    @Override
    public String getDescription() {
        return "Roadmap para o concurso de professor";
    }
    
}
