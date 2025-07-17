package br.ufrn.myway.Model.AbstractClasses;

import br.ufrn.myway.Model.Entities.Banca;
import br.ufrn.myway.Model.Entities.Prova;
import br.ufrn.myway.Model.Enums.NivelConcurso;

public interface ConcursoFactory {
    ConcursoAbstract createConcurso(String edital, NivelConcurso nivel, Banca banca,
                                    Prova prova);
    Banca createBanca(Banca banca);
    Prova createProva(Prova prova);
}
