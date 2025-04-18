package br.ufrn.myway.Model;

import br.ufrn.myway.Model.Enums.GenderPerson;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_person")
public class Person extends AbstractModel {

    @OneToOne(mappedBy = "person")
    @JoinColumn(name = "id_user")
    private User user;

    private String name;

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

    public Person(User user, String name, LocalDate birthDate, GenderPerson gender, String historyDescription,
            String country, String region) {
        this.user = user;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.historyDescription = historyDescription;
        this.country = country;
        this.region = region;
    }

    public Person() {
    }
}
