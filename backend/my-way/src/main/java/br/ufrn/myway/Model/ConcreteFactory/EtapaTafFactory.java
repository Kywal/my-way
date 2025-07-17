package br.ufrn.myway.Model.ConcreteFactory;

import br.ufrn.myway.Model.AbstractClasses.AbstractEtapa;
import br.ufrn.myway.Model.AbstractClasses.EtapaFactory;
import br.ufrn.myway.Model.Entities.EtapaTAF;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Qualifier("etapaTafFactory")
public class EtapaTafFactory implements EtapaFactory {

    @Override
    public AbstractEtapa createEtapa(String description, LocalDateTime startTime, LocalDateTime endTime, List<String> exercicios) {
        EtapaTAF etapaTAF = new EtapaTAF();
        etapaTAF.setDescription(description);
        etapaTAF.setStartTime(startTime);
        etapaTAF.setEndTime(endTime);
        etapaTAF.setExercios(exercicios);
        return etapaTAF;
    }
}
