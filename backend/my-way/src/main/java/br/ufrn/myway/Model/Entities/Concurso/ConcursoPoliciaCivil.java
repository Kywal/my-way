package br.ufrn.myway.Model.Entities.Concurso;

import br.ufrn.myway.Model.Enums.Nivel;
import jakarta.persistence.Entity;

@Entity
public class ConcursoPoliciaCivil extends ConcursoBase {

    @Override
    public Nivel getNivel() {
        return Nivel.SUPERIOR;
    }
}
