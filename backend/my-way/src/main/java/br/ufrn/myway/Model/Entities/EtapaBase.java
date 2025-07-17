package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.AbstractClasses.AbstractEtapa;
import java.time.LocalDateTime;

public class EtapaBase extends AbstractEtapa {

    public EtapaBase() {
        super();
    }

    public EtapaBase(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, startTime, endTime);
    }

}
