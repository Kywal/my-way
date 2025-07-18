package br.ufrn.myway.model.entities.Concurso;

import br.ufrn.myway.model.enums.Nivel;

public interface AbstractConcurso {

    String getOrgao();

    String getCargo();

    Nivel getNivel();

    Long getId();
}
