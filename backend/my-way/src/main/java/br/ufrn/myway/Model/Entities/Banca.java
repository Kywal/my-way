package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.AbstractClasses.ConcursoAbstract;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
@Entity
public class Banca extends AbstractModel{
    private String name;
    @OneToOne(mappedBy = "bancaConcurso")
    private ConcursoAbstract concurso;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
