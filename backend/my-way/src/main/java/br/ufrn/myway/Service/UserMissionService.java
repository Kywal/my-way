package br.ufrn.myway.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.model.entities.Mission;
import br.ufrn.myway.model.entities.User;
import br.ufrn.myway.model.entities.UserMission;
import br.ufrn.myway.model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.UserMissionRepository;

@Service
public class UserMissionService {

    @Autowired
    private UserMissionRepository userMissionRepository;

    @Autowired
    private UserService userService;

    public boolean verifyConcludedQuantityTimeMission(User user, int timeInMinutes) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);

        UserMission userMissionToday = userMissionRepository.findByUserAndDateRange(user, startOfDay, endOfDay);

        if (userMissionToday == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.ERROR_USER_DONT_HAVE_DAILY_MISSION.getMessage(user.getEmail()));
        }

        Mission mission = userMissionToday.getMission();

        if (mission.getTimeInMinutes() <= timeInMinutes) {
            userMissionToday.setCompleted(true);
            userMissionRepository.save(userMissionToday);
            return true;
        }
        return false;
    }

    public UserMission findByUserAndDateRange(String userEmail) {
        User user = userService.findByEmail(userEmail);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);
        UserMission userMissionToday = userMissionRepository.findByUserAndDateRange(user, startOfDay, endOfDay);

        if (userMissionToday == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, ErrorMessageUtils.ERROR_USER_DONT_HAVE_DAILY_MISSION.getMessage(user.getEmail()));
        }
        return userMissionToday;
    }
}
