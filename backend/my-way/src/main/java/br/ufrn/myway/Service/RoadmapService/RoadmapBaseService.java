package br.ufrn.myway.Service.RoadmapService;

import br.ufrn.myway.Repository.Roadmap.RoadmapBaseRepository;
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
