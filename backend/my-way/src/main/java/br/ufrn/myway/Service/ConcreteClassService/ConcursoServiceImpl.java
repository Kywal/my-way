package br.ufrn.myway.Service.ConcreteClassService;

import br.ufrn.myway.Model.AbstractClasses.ConcursoAbstract;
import br.ufrn.myway.Service.InterfaceService.CrudConcursoService;

import java.util.ArrayList;
import java.util.List;

public class ConcursoServiceImpl implements CrudConcursoService<ConcursoAbstract, Long> {


    private final List<ConcursoAbstract> concursos = new ArrayList<>();

    @Override
    public ConcursoAbstract save(ConcursoAbstract entity) {
        if (entity.getId() == null) {

        }
        return null;
    }
    @Override
    public void delete(ConcursoAbstract entity) {

    }
    @Override
    public List<ConcursoAbstract> findAll() {
        return List.of();
    }
}
