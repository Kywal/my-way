package br.ufrn.myway.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.ufrn.myway.model.enums.ErrorMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.model.entities.Mission;
import br.ufrn.myway.model.entities.User;
import br.ufrn.myway.model.entities.UserMission;
import br.ufrn.myway.Repository.MissionRepository;
import br.ufrn.myway.Repository.UserMissionRepository;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserMissionRepository userMissionRepository;

    public List<Mission> listAll() {
        return missionRepository.findAll();
    }

    public Mission findById(Long id) {
        Mission mission = missionRepository.getById(id);
        if(mission == null){
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Mission"));
        }
        return mission;
    }

    public Mission save(Mission mission) {
        return missionRepository.save(mission);
    }

    public void delete(Long id) {
        missionRepository.delete(id);
    }

    public void assignDailyMissionIfNeeded(User user) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.withHour(23).withMinute(59).withSecond(59).withNano(999_000_000);

        boolean alreadyAssigned = userMissionRepository.existsByUserAndDateRange(user, startOfDay, endOfDay);
        System.out.println("Already assigned: " + alreadyAssigned);
        if (alreadyAssigned) {
            return;
        }

        Optional<Mission> dailyMissionOpt = missionRepository.findById(1L);
        System.out.println("Daily mission: " + dailyMissionOpt);
        if (dailyMissionOpt.isEmpty()) {
            return;
        }

        Mission dailyMission = dailyMissionOpt.get();

        UserMission userMission = new UserMission();
        userMission.setUser(user);
        userMission.setMission(dailyMission);
        userMission.setCompleted(false);
        userMission.setStartDate(LocalDateTime.now());
        userMission.setEndDate(endOfDay);

        userMissionRepository.save(userMission);
    }

}
