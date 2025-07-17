package br.ufrn.myway.Model.AbstractClasses;

import java.time.LocalDateTime;
import java.util.List;

public interface EtapaFactory {
    AbstractEtapa createEtapa(String description, LocalDateTime startTime, LocalDateTime endTime, List<String>exercicios);

}
