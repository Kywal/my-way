package br.ufrn.myway.Model.AbstractClasses;

import br.ufrn.myway.Model.Entities.AbstractModel;
import br.ufrn.myway.Model.Entities.Banca;
import br.ufrn.myway.Model.Entities.StudyTopic;
import br.ufrn.myway.Model.Enums.NivelConcurso;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ConcursoBase extends AbstractModel {

    private String edital; //isso define qual a prova

    private Banca bancaConcurso;

    private NivelConcurso nivelConcurso;

    @Override
    public String toString() {
        StringBuilder concursoBase =
                new StringBuilder(
                                "\tEdital: " + edital + "\n" +
                                "\tNivel Concurso: " + nivelConcurso + "\n" +
                                        "\tBanca Concurso: " + bancaConcurso.getName() + "\n"
                );
        return concursoBase.toString();
    }

    public String getEdital() {
        return edital;
    }

    public void setEdital(String edital) {
        this.edital = edital;
    }

    public NivelConcurso getNivelConcurso() {
        return nivelConcurso;
    }

    public void setNivelConcurso(NivelConcurso nivelConcurso) {
        this.nivelConcurso = nivelConcurso;
    }
}
