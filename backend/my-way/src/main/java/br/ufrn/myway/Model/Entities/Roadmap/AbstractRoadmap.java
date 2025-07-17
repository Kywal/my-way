package br.ufrn.myway.Model.Entities.Roadmap;

import java.util.List;

import br.ufrn.myway.Model.Entities.Concurso.AbstractConcurso;
import br.ufrn.myway.Model.Entities.Goal.AbstractGoal; 

public interface AbstractRoadmap {
    public List<AbstractGoal> getGoals();
    public AbstractConcurso getConcurso();
}
