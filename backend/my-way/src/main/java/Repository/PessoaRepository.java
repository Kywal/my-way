package Repository;

import Entities.PessoaClasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaClasse, UUID> {
    Optional<PessoaClasse> findByPessoaEmail(String pessoaEmail);
}
