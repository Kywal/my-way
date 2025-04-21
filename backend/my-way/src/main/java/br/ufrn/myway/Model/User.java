package br.ufrn.myway.Model;

import br.ufrn.myway.Model.Enums.Roles;
import jakarta.persistence.*;

@Table(name = "tb_user")
@Entity
public class User extends AbstractModel {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person")
    private Person person;

    private String email;

    private String password;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    private Roles role;

    private int tokens;

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

    public User() {
    }

    public User(Person person, String email, String password, int tokens) {
        this.person = person;
        this.email = email;
        this.password = password;
        this.tokens = tokens;
    }
}
