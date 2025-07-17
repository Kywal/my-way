package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.AbstractClasses.AbstractEtapa;


import java.time.LocalDateTime;
import java.util.List;

public class EtapaTAF extends AbstractEtapa {

    private List<String> exercios;

    public List<String> getExercios() {
        return exercios;
    }

    public void setExercios(List<String> exercios) {
        this.exercios = exercios;
    }
}
