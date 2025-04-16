package Entities;

import DTO.PersonDTO;

import java.util.UUID;

public class User extends Person{

    private String email;

    private String password;

    private int tokens;

    public User(UUID id, String name, int age, GenderPerson genero, String historyDescription, String country, String region, String email, String password, int tokens) {
        super(id, name, age, genero, historyDescription, country, region);
        this.email = email;
        this.password = password;
        this.tokens = tokens;
    }

    public User(PersonDTO personDTO, String email, String password, int tokens) {
        super(personDTO);
        this.email = email;
        this.password = password;
        this.tokens = tokens;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
}

