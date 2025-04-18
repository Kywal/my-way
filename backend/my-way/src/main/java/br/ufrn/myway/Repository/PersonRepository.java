package br.ufrn.myway.Repository;

import org.springframework.stereotype.Repository;

import br.ufrn.myway.Model.Person; 
 
@Repository
public interface PersonRepository extends SoftDeletableRepository<Person> {

}
