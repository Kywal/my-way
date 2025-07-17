package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.ConcursoGeneral;
import br.ufrn.myway.Repository.ConcursoGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcursoGeneralService {
    @Autowired
    private ConcursoGeneralRepository concursoGeneralRepository;

    public ConcursoGeneralService(ConcursoGeneralRepository concursoGeneralRepository) {
        this.concursoGeneralRepository = concursoGeneralRepository;
    }
    public ConcursoGeneral save(ConcursoGeneral concursoGeneral){
        return concursoGeneralRepository.save(concursoGeneral);
    }
    public void delete(ConcursoGeneral concursoGeneral){
        concursoGeneralRepository.delete(concursoGeneral);
    }
    public List<ConcursoGeneral> listConcursoGeneral(){
        return concursoGeneralRepository.findAll();
    }
}
