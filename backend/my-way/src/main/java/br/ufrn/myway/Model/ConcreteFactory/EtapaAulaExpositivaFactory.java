package br.ufrn.myway.Model.ConcreteFactory;

import br.ufrn.myway.Model.AbstractClasses.AbstractEtapa;
import br.ufrn.myway.Model.AbstractClasses.EtapaFactory;
import br.ufrn.myway.Model.Entities.AulaExpositiva;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
@Qualifier("professorFactory")
public class EtapaAulaExpositivaFactory implements EtapaFactory
{
    @Override
    public AbstractEtapa createEtapa(String description, LocalDateTime startTime, LocalDateTime endTime, List<String> AssuntosPossiveis) {
        AulaExpositiva etapaAulaExpositiva = new AulaExpositiva();
        etapaAulaExpositiva.setDescription(description);
        etapaAulaExpositiva.setStartTime(startTime);
        etapaAulaExpositiva.setEndTime(endTime);
        etapaAulaExpositiva.setAssuntosPossiveis(AssuntosPossiveis);
        return etapaAulaExpositiva;
    }
}
