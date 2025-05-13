package br.ufrn.myway.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.Mission;
import br.ufrn.myway.Repository.MissionRepository;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public List<Mission> listAll() {
        return missionRepository.list();
    }

    public Mission findById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
    }

    public Mission save(Mission mission) {
        return missionRepository.save(mission);
    }

    public void delete(Long id) {
        missionRepository.delete(id);
    }

}
