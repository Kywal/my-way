package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.PomodoroTimer;
import br.ufrn.myway.Model.Enums.PomodoroStatus;
import br.ufrn.myway.Repository.PomodoroTimerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PomodoroTimerService {
    @Autowired
    PomodoroTimerRepository pomodoroTimerRepository;

    public PomodoroTimerService(PomodoroTimerRepository pomodoroTimerRepository) {
        this.pomodoroTimerRepository = pomodoroTimerRepository;
    }
    public PomodoroTimer searchTimer(Long timerId) {
        return pomodoroTimerRepository.findById(timerId)
                .orElseThrow(() -> new RuntimeException("Timer not Found"));
    }

    public PomodoroTimer startStudySession(PomodoroTimer newPomodoroTimer){
        PomodoroTimer pomodoroTimer = searchTimer(newPomodoroTimer.getId());

        pomodoroTimer.setPomodoroStatus(PomodoroStatus.RUNNING);
        pomodoroTimer.setStartTime(LocalDateTime.now());

        return pomodoroTimerRepository.save(pomodoroTimer);
    }
}
