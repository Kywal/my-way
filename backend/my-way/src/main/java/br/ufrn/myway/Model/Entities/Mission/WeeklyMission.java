package br.ufrn.myway.Model.Entities.Mission;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_weekly_mission")
public abstract class WeeklyMission extends Mission{

}
