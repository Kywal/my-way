package br.ufrn.myway;

import br.ufrn.myway.model.entities.Mission;
import br.ufrn.myway.model.Enums.MissionFrequency;
import br.ufrn.myway.model.Enums.MissionType;
import br.ufrn.myway.Repository.MissionRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {
    private final MissionRepository missionRepository;

    public DataInitializer(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (missionRepository.count() == 0) {
            Mission defaultMission = new Mission();
            defaultMission.setTitle("Estudar na plataforma");
            defaultMission.setDescription("Estude no MyWay usando o sistema de estuos pomodoro.");
            defaultMission.setFrequency(MissionFrequency.DAILY);
            defaultMission.setType(MissionType.QUANTITY_TIME);
            defaultMission.setTimeInMinutes(10);
            defaultMission.setRewardPoints(15);

            missionRepository.save(defaultMission);
        }
    }
}
