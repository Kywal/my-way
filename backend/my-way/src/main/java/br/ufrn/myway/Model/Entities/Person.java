package br.ufrn.myway.Model.Entities;

import java.time.LocalDate;

import br.ufrn.myway.Model.Enums.GenderPerson;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_person")
public class Person extends AbstractModel {

    private String name;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private GenderPerson gender;

    private String historyDescription;

    private String aboutMe;

    private String country;

    private String region;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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

    public Person(String name, LocalDate birthday, GenderPerson gender, String historyDescription, String AboutMe, String country, String region) {}
    {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.historyDescription = historyDescription;
        this.aboutMe = aboutMe;
        this.country = country;
        this.region = region;
    }

    public Person() {
    }
}
