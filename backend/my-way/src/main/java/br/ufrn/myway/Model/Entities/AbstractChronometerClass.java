package Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.List;
//cronometros e metodos de estudo ambos são interfaces
//anotação especifica jpa singletable
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorValue("dog") usar isso nas classes que herdam dela
@MappedSuperclass
public abstract class AbstractChronometerClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idChronometer;

    private List<StudyCycleClass> cycles;

    private float totalHoursStudied;

    private int totalCycles;

    public Long getIdChronometer() {
        return idChronometer;
    }

    public void setIdChronometer(Long idChronometer) {
        this.idChronometer = idChronometer;
    }

    public List<StudyCycleClass> getCycles() {
        return cycles;
    }

    public void setCycles(List<StudyCycleClass> cycles) {
        this.cycles = cycles;
    }

    public float getTotalHoursStudied() {
        return totalHoursStudied;
    }

    public void setTotalHoursStudied(float totalHoursStudied) {
        this.totalHoursStudied = totalHoursStudied;
    }

    public int getTotalCycles() {
        return totalCycles;
    }

    public void setTotalCycles(int totalCycles) {
        this.totalCycles = totalCycles;
    }

    public boolean addStudyCycle(StudyCycleClass studyCycleClass, String studyMethod) {
        if (studyMethod == null) {
            return false;
        } else {
            return true;
        }
    }
    public boolean removeStudyCycle(StudyCycleClass studyCycleClass){
        return false;
    }
}
