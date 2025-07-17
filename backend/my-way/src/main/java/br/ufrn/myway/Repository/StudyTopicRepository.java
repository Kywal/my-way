package br.ufrn.myway.Repository;

import org.springframework.stereotype.Repository;

import br.ufrn.myway.Model.Entities.StudyTopic; 

@Repository
public interface StudyTopicRepository extends SoftDeletableRepository<StudyTopic> {
}
