package br.ufrn.myway.Service;

import br.ufrn.myway.Model.Entities.ConcursoProfessor;
import br.ufrn.myway.Repository.ConcursoProfessorRepository;
import br.ufrn.myway.Repository.ConcursoProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcursoProfessorService {
    @Autowired
    private ConcursoProfessorRepository concursoProfessorRepository;

    public ConcursoProfessorService(ConcursoProfessorRepository concursoProfessorRepository) {
        this.concursoProfessorRepository = ConcursoProfessorService.this.concursoProfessorRepository;
    }
    public ConcursoProfessor save(ConcursoProfessor concursoProfessor){
        return concursoProfessorRepository.save(concursoProfessor);
    }
    public void delete(ConcursoProfessor concursoProfessor){
        concursoProfessorRepository.delete(concursoProfessor);
    }
    public List<ConcursoProfessor> listConcursoProfessor(){
        return concursoProfessorRepository.findAll();
    }

}
