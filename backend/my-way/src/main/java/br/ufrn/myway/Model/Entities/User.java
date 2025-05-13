package br.ufrn.myway.Model.Entities;

import br.ufrn.myway.Model.Enums.Roles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;

@Table(name = "tb_user")
@Entity
public class User extends AbstractModel {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person")
    private Person person;

    private String email;

    private String password;

    private Roles role;

    private int tokens;

    @ManyToMany(mappedBy = "users")
    private List<Mission> missions;

    public User() {
    }

    public User(Person person, String email, String password, int tokens) {
        this.person = person;
        this.email = email;
        this.password = password;
        this.tokens = tokens;
    }

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

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }
}
