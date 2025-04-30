package br.ufrn.myway.Model.Entities.Mission;

public class QuantityTimeMission extends DailyMission{

    private int timeInMinutes;

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
}
