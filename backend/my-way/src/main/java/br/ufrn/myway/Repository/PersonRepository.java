package br.ufrn.myway.Repository;

import org.springframework.stereotype.Repository;

import br.ufrn.myway.model.entities.Person;
 
@Repository
public interface PersonRepository extends SoftDeletableRepository<Person> {

}
