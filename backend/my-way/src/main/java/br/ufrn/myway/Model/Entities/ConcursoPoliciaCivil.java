package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.AbstractClasses.ConcursoAbstract;
import br.ufrn.myway.Model.Enums.NivelConcurso;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PoliciaCivil")
    public class ConcursoPoliciaCivil extends ConcursoAbstract {

    @Override
    public ConcursoAbstract createConcurso(String edital, NivelConcurso nivel, Banca banca, Prova prova) {
        return null;
    }
}
