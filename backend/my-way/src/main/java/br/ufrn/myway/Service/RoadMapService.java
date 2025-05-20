package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.Goal;
import br.ufrn.myway.Model.Entities.RoadMap;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.GoalRepository;
import br.ufrn.myway.Repository.RoadMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoadMapService {

    @Autowired
    private RoadMapRepository roadMapRepository;


    public RoadMap findById(Long id){
        RoadMap roadMap = roadMapRepository.getById(id);
        if(roadMap == null){
            throw new BusinessException(ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("RoadMap"));
        }
        return roadMap;
    }

    public RoadMap save(RoadMap roadMap){
        return roadMapRepository.save(roadMap);
    }

    public List<RoadMap> list(){
        return roadMapRepository.list();
    }

    public void deletar(Long id){
        roadMapRepository.delete(id);
    }
}
