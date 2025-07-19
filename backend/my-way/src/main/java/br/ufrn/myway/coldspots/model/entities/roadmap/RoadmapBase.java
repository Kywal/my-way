package br.ufrn.myway.coldspots.model.entities.roadmap;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.myway.coldspots.model.entities.AbstractModel;
import br.ufrn.myway.coldspots.model.entities.Concurso.AbstractConcurso;
import br.ufrn.myway.coldspots.model.entities.Concurso.ConcursoBase;
import br.ufrn.myway.coldspots.model.entities.Goal.AbstractGoal;
import br.ufrn.myway.coldspots.model.entities.Goal.GoalBase;
import br.ufrn.myway.coldspots.model.entities.User;
import br.ufrn.myway.coldspots.model.enums.RoadmapStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RoadmapBase extends AbstractModel implements AbstractRoadmap {

    @ManyToOne
    private ConcursoBase concurso;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(nullable = false)
    private String mainGoal;

    private String description;

    @OrderBy("roadmapIndex")
    @OneToMany(mappedBy = "roadmap", cascade = CascadeType.ALL)
    public List<GoalBase> goals = new ArrayList<GoalBase>();

    private RoadmapStatus status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(String name) {
        this.mainGoal = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<GoalBase> getGoals() {
        return goals;
    }

    public void setGoals(List<GoalBase> listGoals) {
        this.goals = listGoals;
    }

    public RoadmapStatus getStatus() {
        return status;
    }

    public void setStatus(RoadmapStatus status) {
        this.status = status;
    }

    @Override
    public AbstractConcurso getConcurso() {
        return concurso;
    }

    public void setConcurso(ConcursoBase concurso) {
        this.concurso = concurso;
    }

    @Override
    public String toString() {
        StringBuilder roadmap
                = new StringBuilder(
                        "\n- Roadmap para " + mainGoal + " -\n"
                        + "\nDescrição: " + description + "\n"
                        + "Status: " + status
                        + "\n"
                        + "Objetivos:\n"
                );

        for (AbstractGoal g : goals) {
            roadmap.append(g);
        }

        return roadmap.toString();
    }
}
