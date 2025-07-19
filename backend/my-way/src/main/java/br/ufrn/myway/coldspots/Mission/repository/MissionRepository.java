package br.ufrn.myway.coldspots.Mission.repository;

import java.util.List;

import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.coldspots.Mission.model.Mission;

@Repository
public interface MissionRepository extends SoftDeletableRepository<Mission> {

    List<Mission> findByTitle(String title);

}
