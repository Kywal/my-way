package br.ufrn.myway.coldspots.model.entities;

import br.ufrn.myway.coldspots.Mission.model.UserMission;
import br.ufrn.myway.coldspots.model.enums.Roles;
import jakarta.persistence.*;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissions = new ArrayList<>();

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

    public List<UserMission> getUserMissions() {
        return userMissions;
    }

    public void setUserMissions(List<UserMission> userMissions) {
        this.userMissions = userMissions;
    }
}
