package br.ufrn.myway.coldspots.User.repository;

import br.ufrn.myway.coldspots.Common.repository.SoftDeletableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.myway.model.entities.User;

@Repository
public interface UserRepository extends SoftDeletableRepository<User> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

}
