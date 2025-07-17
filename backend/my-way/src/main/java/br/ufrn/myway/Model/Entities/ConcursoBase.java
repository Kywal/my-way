package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Enums.NivelConcurso;
import br.ufrn.myway.Model.Interfaces.AbstractConcurso;
import jakarta.persistence.*;

@Entity
public class ConcursoBase implements AbstractConcurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String edital;

    private NivelConcurso nivelConcurso;

    @OneToOne
    @JoinColumn(name = "banca_id")
    private Banca banca;

    @ManyToOne
    @JoinColumn(name = "prova_id")
    private Prova prova;

    public Banca getBanca() {
        return banca;
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
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

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }
}
