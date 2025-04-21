package br.ufrn.myway.Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import br.ufrn.myway.Model.AbstractModel;

import java.util.List;

@NoRepositoryBean
public interface SoftDeletableRepository<M extends AbstractModel> extends AbstractRepository<M> {
    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} e SET e.ativo = false WHERE e.id = :id")
    public void delete(@Param("id")Long id);

    @Query("SELECT e FROM #{#entityName} e WHERE e.ativo = true")
    public List<M> list();

    @Query("SELECT e FROM #{#entityName} e WHERE e.ativo = true AND e.id = :id")
    @Override
    public M getById(@Param("id")Long id);
}
