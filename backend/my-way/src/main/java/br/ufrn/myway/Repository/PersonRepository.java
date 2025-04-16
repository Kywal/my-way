package br.ufrn.myway.Repository;

import br.ufrn.myway.Model.Entities.Person;
import br.ufrn.myway.Model.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends SoftDeletableRepository<Person> {

}
