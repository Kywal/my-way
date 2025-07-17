package br.ufrn.myway.Model.AbstractClasses;

import br.ufrn.myway.Model.Entities.AbstractModel;
import br.ufrn.myway.Model.Entities.Banca;
import br.ufrn.myway.Model.Entities.Prova;
import br.ufrn.myway.Model.Enums.NivelConcurso;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_concurso")public abstract class ConcursoAbstract extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String edital; //isso define qual a prova

    @OneToOne
    @JoinColumn(name = "banca_id", referencedColumnName = "id")
    private Banca bancaConcurso;

    @ManyToOne
    @JoinColumn(name = "prova_id", referencedColumnName = "id")
    private Prova prova;

    private NivelConcurso nivelConcurso;
    /*
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
    */

    public ConcursoAbstract(Long id, String edital, Banca bancaConcurso, Prova prova, NivelConcurso nivelConcurso) {
        this.id = id;
        this.edital = edital;
        this.bancaConcurso = bancaConcurso;
        this.prova = prova;
        this.nivelConcurso = nivelConcurso;
    }

    public ConcursoAbstract() {

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Banca getBancaConcurso() {
        return bancaConcurso;
    }

    public void setBancaConcurso(Banca bancaConcurso) {
        this.bancaConcurso = bancaConcurso;
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public abstract ConcursoAbstract createConcurso(String edital, NivelConcurso nivel, Banca banca,
                                                    Prova prova);
}
