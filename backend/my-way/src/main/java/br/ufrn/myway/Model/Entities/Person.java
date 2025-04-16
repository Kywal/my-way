package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.DTO.PersonDTO;
import br.ufrn.myway.Model.Enums.GenderPerson;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_person")
public class Person extends AbstractModel {

    @Column
    private String name;

    @Column
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private GenderPerson gender;

    private String historyDescription;

    private String country;

    private String region;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate age) {
        this.birthDate = age;
    }

    public GenderPerson getGender() {
        return gender;
    }

    public void setGender(GenderPerson gender) {
        this.gender = gender;
    }

    public String getHistoryDescription() {
        return historyDescription;
    }

    public void setHistoryDescription(String historyDescription) {
        this.historyDescription = historyDescription;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


}
