package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.AbstractClasses.AbstractEtapa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("AULA_EXP")
public class AulaExpositiva extends AbstractEtapa {

    private List<String> assuntosPossiveis;

    public List<String> getAssuntosPossiveis() {
        return assuntosPossiveis;
    }

    public void setAssuntosPossiveis(List<String> assuntosPossiveis) {
        this.assuntosPossiveis = assuntosPossiveis;
    }
}
