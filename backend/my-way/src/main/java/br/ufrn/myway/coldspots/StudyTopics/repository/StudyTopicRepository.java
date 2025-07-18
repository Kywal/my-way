package br.ufrn.myway.coldspots.StudyTopics.repository;

import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.model.entities.StudyTopic;

@Repository
public interface StudyTopicRepository extends SoftDeletableRepository<StudyTopic> {
}
