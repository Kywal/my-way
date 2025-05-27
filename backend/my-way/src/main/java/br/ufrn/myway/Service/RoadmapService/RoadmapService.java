package br.ufrn.myway.Service.RoadmapService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.Roadmap;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Enums.RoadMapStatus;
import br.ufrn.myway.Repository.RoadmapRepository;
import br.ufrn.myway.Service.BusinessException;
import br.ufrn.myway.Service.UserService;

@Service
public class RoadmapService {

    @Autowired
    private RoadmapRepository roadmapRepository;

    @Autowired
    private UserService userService;

    public Roadmap findById(Long id) {
        Roadmap roadMap = roadmapRepository.getById(id);
        if (roadMap == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"));
        }
        return roadMap;
    }

    public Roadmap save(Roadmap roadmap, Long id) {
        User user = userService.findById(id);
        if (roadmap.getUser() == null) {
            roadmap.setUser(user);
        }
        if (roadmap.getStatus() == null) {
            roadmap.setStatus(RoadMapStatus.ACTIVE);
        }

        return roadmapRepository.save(roadmap);
    }

    public List<Roadmap> list() {
        return roadmapRepository.list();
    }

    public void deletar(Long id) {
        roadmapRepository.delete(id);
    }

    public List<Roadmap> findRoadMapByUser(Long id) {
        return roadmapRepository.findRoadMapByUser(id);
    }

    public Roadmap cancelRoadmap(Long id) {
        Roadmap roadmap = findById(id);
        roadmap.setStatus(RoadMapStatus.CANCELLED);
        return save(roadmap, roadmap.getUser().getId());
    }

    public Roadmap finishRoadmap(Long id) {
        Roadmap roadmap = findById(id);
        roadmap.setStatus(RoadMapStatus.CONCLUDED);
        return save(roadmap, roadmap.getUser().getId());
    }

    public Roadmap getByStatus(Long id, RoadMapStatus status) {
        Roadmap roadmap = roadmapRepository.findByStatus(id, status);
        if (roadmap == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("Roadmap"));
        }
        return roadmap;
    }
}
