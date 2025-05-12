package Service;

import Entities.PomodoroClass;
import Entities.PomodoroStatus;
import Repositories.PomodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PomodoroService {
    @Autowired
    PomodoRepository pomodoRepository;

    public PomodoroService(PomodoRepository pomodoRepository) {
        this.pomodoRepository = pomodoRepository;
    }

    public PomodoroClass searchTimer(Long timerId) {
        return pomodoRepository.findById(timerId)
                .orElseThrow(() -> new RuntimeException("Timer not Found"));
    }

    public PomodoroClass startStudySession(Long timerId){
        PomodoroClass pomodoroTimer = searchTimer(timerId);

        pomodoroTimer.setPomodoroStatus(PomodoroStatus.RUNNING);
        pomodoroTimer.setStartTime(LocalDateTime.now());

        return pomodoRepository.save(pomodoroTimer);
    }
    public PomodoroClass starBreakSession(Long timerId, boolean isLongBreak){
        PomodoroClass pomodoroTimer = searchTimer(timerId);

        if (isLongBreak == true) {
            pomodoroTimer.setPomodoroStatus(PomodoroStatus.LONG_BREAK);
            pomodoroTimer.setCompletedPomodoros(0);
        }else{
            pomodoroTimer.setPomodoroStatus(PomodoroStatus.BREAK);
            pomodoroTimer.setCompletedPomodoros(pomodoroTimer.getCyclesBeforeLongBreak()+1);
            }
        pomodoroTimer.setStartTime(LocalDateTime.now());
        return pomodoRepository.save(pomodoroTimer);
    }

    public PomodoroClass stopTimer(Long timerId) {
        PomodoroClass pomodoroTimer = searchTimer(timerId);
        pomodoroTimer.setPomodoroStatus(PomodoroStatus.STOPPED);
        return pomodoRepository.save(pomodoroTimer);
    }
    public PomodoroClass getRemainingTime(Long timerId){
        PomodoroClass pomodoroTimer = searchTimer(timerId);
        if (pomodoroTimer.getPomodoroStatus() == PomodoroStatus.STOPPED)

        {
            return pomodoroTimer;
        }
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(pomodoroTimer.getStartTime(),now);
        long secondsPassed = duration.getSeconds();

        long totalSeconds = 0;
        if (pomodoroTimer.getPomodoroStatus() == PomodoroStatus.RUNNING) {
            totalSeconds = pomodoroTimer.getUpTime() * 60;
        } else if (pomodoroTimer.getPomodoroStatus() == PomodoroStatus.BREAK) {
            totalSeconds = pomodoroTimer.getDownTime() * 60;
        }else if (pomodoroTimer.getPomodoroStatus() == PomodoroStatus.LONG_BREAK) {
            totalSeconds = pomodoroTimer.getLongDownTime() * 60;
        }
        long remainningSecond = totalSeconds - secondsPassed;
            if(remainningSecond <=0) {
                if (pomodoroTimer.getPomodoroStatus() == pomodoroTimer.getPomodoroStatus().RUNNING) {
                    boolean isLongBreak = pomodoroTimer.getCyclesBeforeLongBreak() + 1 >=
                            pomodoroTimer.getCompletedPomodoros();
                    return starBreakSession(timerId, isLongBreak);
                } else {
                    return startStudySession(timerId);
                }
            }
            return pomodoroTimer;
    }
}
