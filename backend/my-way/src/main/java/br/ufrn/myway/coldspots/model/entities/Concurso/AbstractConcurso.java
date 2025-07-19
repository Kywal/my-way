package br.ufrn.myway.coldspots.model.entities.Concurso;

import br.ufrn.myway.coldspots.model.enums.Nivel;

public interface AbstractConcurso {

    String getOrgao();

    String getCargo();

    Nivel getNivel();

    Long getId();
}
