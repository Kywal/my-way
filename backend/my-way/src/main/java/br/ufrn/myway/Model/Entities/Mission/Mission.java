package br.ufrn.myway.Model.Entities.Mission;

import br.ufrn.myway.Model.Entities.AbstractModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Table(name = "tb_mission")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mission extends AbstractModel {

    private String title;

    private String description;

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

}
