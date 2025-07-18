package br.ufrn.myway.model.entities.roadmap;

import java.util.List;

import br.ufrn.myway.model.entities.Concurso.AbstractConcurso;
import br.ufrn.myway.model.entities.Goal.GoalBase;

public interface AbstractRoadmap {
    public List<GoalBase> getGoals();
    public AbstractConcurso getConcurso();
}
