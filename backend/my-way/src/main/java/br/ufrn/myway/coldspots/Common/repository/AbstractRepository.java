package br.ufrn.myway.coldspots.Common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.ufrn.myway.model.entities.AbstractModel;

@NoRepositoryBean
public interface AbstractRepository<M extends AbstractModel> extends JpaRepository<M, Long> {}
