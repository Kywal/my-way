package br.ufrn.myway.coldspots.model.entities.Concurso;

import br.ufrn.myway.coldspots.model.enums.Nivel;

public class ConcursoProfessor extends ConcursoBase {

    @Override
    public Nivel getNivel() {
        return Nivel.SUPERIOR;
    }
}
