package br.ufrn.myway.Repository;

import br.ufrn.myway.Model.Entities.RoadMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadMapRepository extends JpaRepository<RoadMap,Long> {
}
