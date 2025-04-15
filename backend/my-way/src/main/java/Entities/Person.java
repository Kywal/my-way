package Entities;

import DTO.PessoaDTO;
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
    private GeneroPessoa gender;

    private String historyDescription;

    private String country;

    private String region;

    public Person() {}

    public Person(UUID id, String name, int age, GeneroPessoa genero, String historyDescription, String country, String region) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = genero;
        this.historyDescription = historyDescription;
        this.country = country;
        this.region = region;
    }

    public Person(PessoaDTO pessoaDTO){
        this.name = pessoaDTO.pessoaNome();
        this.age = pessoaDTO.pessoaIdade();
        this.gender = pessoaDTO.pessoaGenero();
        this.historyDescription = pessoaDTO.historyDescription();
        this.country = pessoaDTO.pessoaPais();
        this.region = pessoaDTO.pessoaEstado();
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

    public GeneroPessoa getGender() {
        return gender;
    }

    public void setGender(GeneroPessoa gender) {
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
