package br.ufrn.myway.model.entities.Concurso;

import br.ufrn.myway.model.entities.AbstractModel;
import br.ufrn.myway.model.enums.Nivel;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ConcursoBase extends AbstractModel implements AbstractConcurso {

    private String orgao;
    private String cargo;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @Override
    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    @Override
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
