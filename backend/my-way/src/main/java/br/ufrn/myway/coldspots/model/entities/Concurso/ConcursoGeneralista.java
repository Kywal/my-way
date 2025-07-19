package br.ufrn.myway.coldspots.model.entities.Concurso;

import br.ufrn.myway.coldspots.model.enums.Nivel;

public class ConcursoGeneralista extends ConcursoBase {

    @Override
    public Nivel getNivel() {
        return Nivel.MEDIO;
    }
}
