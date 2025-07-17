package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.AbstractClasses.AbstractEtapa;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;
@Entity
public class EtapaBase extends AbstractEtapa {

    public EtapaBase() {
        super();
    }
    public EtapaBase(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, startTime, endTime);
    }

}
