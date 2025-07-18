package br.ufrn.myway.model.entities.Concurso;

import br.ufrn.myway.model.Enums.Nivel;
import jakarta.persistence.Entity;

@Entity
public class ConcursoPoliciaCivil extends ConcursoBase {

    @Override
    public Nivel getNivel() {
        return Nivel.SUPERIOR;
    }
}
