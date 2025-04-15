package Repository;

import Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByPessoaEmail(String pessoaEmail);
}
