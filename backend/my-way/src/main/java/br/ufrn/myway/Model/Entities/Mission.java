package br.ufrn.myway.Model.Entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.ufrn.myway.Model.Enums.MissionFrequency;
import br.ufrn.myway.Model.Enums.MissionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Table(name = "tb_mission")
@Entity
public class Mission extends AbstractModel {

    private String title;

    private String description;

    private int rewardPoints;

    private boolean completed = false;

    @Enumerated(EnumType.STRING)
    private MissionFrequency frequency;

    @Enumerated(EnumType.STRING)
    private MissionType type;

    private Integer quantityGoal;

    private Integer timeInMinutes;

    @ManyToMany
    @JoinTable(
            name = "tb_mission_user",
            joinColumns = @JoinColumn(name = "mission_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;

    }

    public Integer getQuantityGoal() {
        return quantityGoal;
    }

    public void setQuantityGoal(Integer quantityGoal) {
        this.quantityGoal = quantityGoal;
    }

    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public MissionFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(MissionFrequency frequency) {
        this.frequency = frequency;
    }

    public MissionType getType() {
        return type;
    }

    public void setType(MissionType type) {
        this.type = type;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getMissions().add(this);
    }

}
