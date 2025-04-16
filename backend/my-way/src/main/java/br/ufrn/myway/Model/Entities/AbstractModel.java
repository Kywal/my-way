package br.ufrn.myway.Model.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public abstract class AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    @Column(updatable = false)

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDate createdAt = LocalDate.now();
    protected boolean ativo = true;

}
