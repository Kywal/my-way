package br.ufrn.myway.Model.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Prova {
    private String nome;

    private LocalDateTime firstDay;
    private LocalDateTime lastDay;

    private List<String> etapa;

    public Prova(String nome, LocalDateTime firstDay, LocalDateTime lastDay, List<String> etapa) {
        this.nome = nome;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.etapa = etapa;
    }

    public Prova() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(LocalDateTime firstDay) {
        this.firstDay = firstDay;
    }

    public LocalDateTime getLastDay() {
        return lastDay;
    }

    public void setLastDay(LocalDateTime lastDay) {
        this.lastDay = lastDay;
    }

    public List<String> getEtapa() {
        return etapa;
    }

    public void setEtapa(List<String> etapa) {
        this.etapa = etapa;
    }
}
