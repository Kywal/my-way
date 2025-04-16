package br.ufrn.myway.Model.Entities;


import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "tb_user")
@Entity
public class User extends AbstractModel{

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    private String email;

    private String password;

    private int tokens;

    private boolean ativo = true;

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

