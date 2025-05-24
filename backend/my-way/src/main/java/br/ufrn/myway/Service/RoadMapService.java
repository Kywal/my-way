package br.ufrn.myway.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.RoadMap;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.RoadMapRepository;

@Service
public class RoadMapService {

    @Autowired
    private RoadMapRepository roadMapRepository;

    @Autowired
    private UserService userService;

    public RoadMap findById(Long id) {
        RoadMap roadMap = roadMapRepository.getById(id);
        if (roadMap == null) {
            throw new BusinessException(ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("RoadMap"));
        }
        return roadMap;
    }

    public RoadMap save(RoadMap roadMap, Long id) {
        User user = userService.findById(id);
        roadMap.setUser(user);

        return roadMapRepository.save(roadMap);
    }

    public List<RoadMap> list() {
        return roadMapRepository.list();
    }

    public void deletar(Long id) {
        roadMapRepository.delete(id);
    }
}
