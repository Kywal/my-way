package br.ufrn.myway.Model.Entities.Concurso;

import br.ufrn.myway.Model.Enums.Nivel;

public class GeneralConcurso extends ConcursoBase {

    @Override
    public Nivel getNivel() {
        return Nivel.MEDIO;
    }
}
