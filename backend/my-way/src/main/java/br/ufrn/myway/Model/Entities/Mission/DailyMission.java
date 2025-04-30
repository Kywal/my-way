package br.ufrn.myway.Model.Entities.Mission;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_daily_mission")
public abstract class DailyMission extends Mission {

}
