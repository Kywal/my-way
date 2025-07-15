package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Interfaces.AbstractEtapa;

import java.time.LocalDateTime;

public class EtapaBase implements AbstractEtapa {
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EtapaBase(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public EtapaBase() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
