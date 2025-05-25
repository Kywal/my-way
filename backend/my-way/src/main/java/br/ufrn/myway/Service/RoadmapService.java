package br.ufrn.myway.Service;

import java.util.List;

import br.ufrn.myway.Model.Entities.Roadmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Model.Enums.RoadMapStatus;
import br.ufrn.myway.Repository.RoadmapRepository;

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

    public Roadmap save(Roadmap roadMap, Long id) {
        User user = userService.findById(id);
        roadMap.setUser(user);
        roadMap.setStatus(RoadMapStatus.ACTIVE);

        return roadmapRepository.save(roadMap);
    }

    public List<Roadmap> list() {
        return roadmapRepository.list();
    }

    public void deletar(Long id) {
        roadmapRepository.delete(id);
    }
}
