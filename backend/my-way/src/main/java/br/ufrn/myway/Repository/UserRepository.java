package br.ufrn.myway.Repository;

import br.ufrn.myway.Model.Entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends SoftDeletableRepository<User> {

    Optional<User> findByEmail(String email);

}
