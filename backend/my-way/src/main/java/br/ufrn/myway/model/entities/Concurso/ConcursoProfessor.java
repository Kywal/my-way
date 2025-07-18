package br.ufrn.myway.model.entities.Concurso;

import br.ufrn.myway.model.enums.Nivel;

public class ConcursoProfessor extends ConcursoBase {

    @Override
    public Nivel getNivel() {
        return Nivel.SUPERIOR;
    }
}
