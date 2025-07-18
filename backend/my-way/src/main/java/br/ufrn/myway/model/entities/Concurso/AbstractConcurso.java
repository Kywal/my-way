package br.ufrn.myway.model.entities.Concurso;

import br.ufrn.myway.model.Enums.Nivel;

public interface AbstractConcurso {

    String getOrgao();

    String getCargo();

    Nivel getNivel();

    Long getId();
}
