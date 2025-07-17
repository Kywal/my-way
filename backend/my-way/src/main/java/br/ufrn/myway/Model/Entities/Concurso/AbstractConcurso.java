package br.ufrn.myway.Model.Entities.Concurso;

import br.ufrn.myway.Model.Enums.Nivel;

public interface AbstractConcurso {

    String getOrgao();

    String getCargo();

    Nivel getNivel();

    Long getId();
}
