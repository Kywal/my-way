package br.ufrn.myway.coldspots.Person.repository;

import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.model.entities.Person;
 
@Repository
public interface PersonRepository extends SoftDeletableRepository<Person> {

}
