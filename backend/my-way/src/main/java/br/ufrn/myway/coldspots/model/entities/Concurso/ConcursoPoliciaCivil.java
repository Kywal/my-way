package br.ufrn.myway.coldspots.model.entities.Concurso;

import br.ufrn.myway.coldspots.model.enums.Nivel;
import jakarta.persistence.Entity;

@Entity
public class ConcursoPoliciaCivil extends ConcursoBase {

    @Override
    public Nivel getNivel() {
        return Nivel.SUPERIOR;
    }
}
