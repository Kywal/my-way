package Entities;

import DTO.PersonDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column
    private String name;

    @Column
    private int age;

    @Enumerated(EnumType.STRING)
    private GenderPerson gender;

    private String historyDescription;

    private String country;

    private String region;

    public Person() {}

    public Person(UUID id, String name, int age, GenderPerson genero, String historyDescription, String country, String region) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = genero;
        this.historyDescription = historyDescription;
        this.country = country;
        this.region = region;
    }

    public Person(PersonDTO personDTO){
        this.name = personDTO.name();
        this.age = personDTO.age();
        this.gender = personDTO.gender();
        this.historyDescription = personDTO.historyDescription();
        this.country = personDTO.country();
        this.region = personDTO.region();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
