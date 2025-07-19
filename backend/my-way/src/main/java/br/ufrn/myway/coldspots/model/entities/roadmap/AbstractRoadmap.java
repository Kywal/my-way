package br.ufrn.myway.coldspots.model.entities.roadmap;

import java.util.List;

import br.ufrn.myway.coldspots.model.entities.Concurso.AbstractConcurso;
import br.ufrn.myway.coldspots.model.entities.Goal.GoalBase;

public interface AbstractRoadmap {
    public List<GoalBase> getGoals();
    public AbstractConcurso getConcurso();
}
