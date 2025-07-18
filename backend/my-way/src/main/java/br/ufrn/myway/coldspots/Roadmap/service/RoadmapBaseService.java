package br.ufrn.myway.coldspots.Roadmap.service;

import br.ufrn.myway.coldspots.Roadmap.repository.RoadmapBaseRepository;
import jakarta.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@MappedSuperclass
public abstract class RoadmapBaseService {

    @Autowired
    private RoadmapBaseRepository roadmapRepository;

    public void remove(Long id) {
        roadmapRepository.delete(id);
    }

}
