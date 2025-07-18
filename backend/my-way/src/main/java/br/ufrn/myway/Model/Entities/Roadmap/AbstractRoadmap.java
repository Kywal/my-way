package br.ufrn.myway.Model.Entities.Roadmap;

import java.util.List;

import br.ufrn.myway.Model.Entities.Concurso.AbstractConcurso;
import br.ufrn.myway.Model.Entities.Goal.GoalBase;

public interface AbstractRoadmap {
    public List<GoalBase> getGoals();
    public AbstractConcurso getConcurso();
}
