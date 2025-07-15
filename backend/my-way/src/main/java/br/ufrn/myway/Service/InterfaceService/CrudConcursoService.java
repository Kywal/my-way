package br.ufrn.myway.Service.InterfaceService;

import java.util.List;

public interface CrudConcursoService<T, ID>{
    T save(T entity);
    void delete (T entity);
    List<T> findAll();
}
