package br.ufrn.myway.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.Mission;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Entities.UserMission;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.UserMissionRepository;

@Service
public class UserMissionService {
    @Autowired
    private UserMissionRepository userMissionRepository;

    public boolean verifyConcludedQuantityTimeMission(User user, int timeInMinutes) {
        System.out.println("user id:" + user.getEmail());
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);
        System.out.println("startOfDay:" + startOfDay);
        System.out.println("endOfDay:" + endOfDay);

        UserMission userMissionToday = userMissionRepository.findByUserAndDateRange(user, startOfDay, endOfDay);

        if (userMissionToday == null) {
            throw new BusinessException(ErrorMessageUtils.ERROR_USER_DONT_HAVE_DAILY_MISSION.getMessage(user.getEmail()));
        }

        Mission mission = userMissionToday.getMission();

        if(mission.getTimeInMinutes() <= timeInMinutes) {
            userMissionToday.setCompleted(true);
            userMissionRepository.save(userMissionToday);
            return true;
        }
        return false;
    }
}
