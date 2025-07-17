/*

package br.ufrn.myway.Model.ConcreteFactory;

import br.ufrn.myway.Model.AbstractClasses.AbstractEtapa;
import br.ufrn.myway.Model.AbstractClasses.EtapaFactory;
import br.ufrn.myway.Model.Entities.AvalicaoEscrita;
import br.ufrn.myway.Model.Entities.EtapaTAF;
import br.ufrn.myway.Model.Enums.TipoAvaliacaoEscrita;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Qualifier("etapaProvaEscrita")
public class EtapaProvaEscritaFactory implements EtapaFactory {

    @Override
    public AbstractEtapa createEtapa(String description, LocalDateTime startTime, LocalDateTime endTime, TipoAvaliacaoEscrita tipoAvaliacaoEscrita) {
            AvalicaoEscrita etapaEscrita = new AvalicaoEscrita();
            etapaEscrita.setDescription(description);
            etapaEscrita.setStartTime(startTime);
            etapaEscrita.setEndTime(endTime);
            etapaEscrita.setTipoAvaliacaoEscrita(tipoAvaliacaoEscrita);
            return etapaEscrita;
    }
}
*/