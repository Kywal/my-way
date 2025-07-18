package br.ufrn.myway.hotspots.ConcursoProfessor.RoadmapConcursoProfessor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.ufrn.myway.coldspots.Exceptions.BusinessException;
import br.ufrn.myway.coldspots.Roadmap.service.AbstractRoadmapService;
import br.ufrn.myway.coldspots.Roadmap.service.RoadmapBaseService;
import br.ufrn.myway.hotspots.ConcursoProfessor.RoadmapConcursoProfessor.repository.RoadmapConcursoProfessorRepository; 
import br.ufrn.myway.model.entities.roadmap.RoadmapConcursoProfessor;
import br.ufrn.myway.model.enums.ErrorMessageUtils;
import br.ufrn.myway.model.enums.RoadmapStatus;

public class RoadmapConcursoProfessorService extends RoadmapBaseService implements AbstractRoadmapService<RoadmapConcursoProfessor> {

    @Autowired
    private RoadmapConcursoProfessorRepository roadmapRepository;

    @Override
    public RoadmapConcursoProfessor save(RoadmapConcursoProfessor roadmapConcursoProfessor, Long userId) {
        roadmapConcursoProfessor.setId(userId);
        roadmapConcursoProfessor.setStatus(RoadmapStatus.ACTIVE);
        return roadmapRepository.save(roadmapConcursoProfessor);
    }

    @Override
    public RoadmapConcursoProfessor findById(Long userId) {
        return roadmapRepository.findById(userId).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"))
        );
    }

    @Override
    public List<RoadmapConcursoProfessor> findAll() {
        return roadmapRepository.findAll();
    }

    @Override
    public List<RoadmapConcursoProfessor> findRoadmapByUser(Long userId) {
        return roadmapRepository.findRoadmapByUser(userId);
    }

    @Override
    public RoadmapConcursoProfessor cancelRoadmap(Long roadmapId) {
        RoadmapConcursoProfessor roadmap = findById(roadmapId);
        roadmap.setStatus(RoadmapStatus.CANCELLED);

        return save(roadmap, roadmap.getUser().getId());
    }

    @Override
    public RoadmapConcursoProfessor finishRoadmap(Long id) {
        RoadmapConcursoProfessor roadmap = findById(id);
        roadmap.setStatus(RoadmapStatus.CONCLUDED);

        return save(roadmap, roadmap.getUser().getId());
    }

    @Override
    public RoadmapConcursoProfessor getByStatus(Long userId, RoadmapStatus status) {
        return roadmapRepository.findByStatus(userId, status).orElseThrow(
                () -> new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"))
        );
    }
}
