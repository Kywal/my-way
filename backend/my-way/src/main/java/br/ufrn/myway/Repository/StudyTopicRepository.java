package br.ufrn.myway.Repository;

import br.ufrn.myway.Model.Entities.StudyTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyTopicRepository extends JpaRepository<StudyTopic,Long> {
}
