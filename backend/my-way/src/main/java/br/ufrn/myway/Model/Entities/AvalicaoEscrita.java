package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Enums.TipoAvaliacaoEscrita;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AVALIACAO_ESCRITA")
public class AvalicaoEscrita extends EtapaBase{
    private TipoAvaliacaoEscrita tipoAvaliacaoEscrita;

    public TipoAvaliacaoEscrita getTipoAvaliacaoEscrita() {
        return tipoAvaliacaoEscrita;
    }

    public void setTipoAvaliacaoEscrita(TipoAvaliacaoEscrita tipoAvaliacaoEscrita) {
        this.tipoAvaliacaoEscrita = tipoAvaliacaoEscrita;
    }
}
