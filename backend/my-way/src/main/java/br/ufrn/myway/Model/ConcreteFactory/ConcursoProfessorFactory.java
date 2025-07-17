package br.ufrn.myway.Model.ConcreteFactory;

import br.ufrn.myway.Model.AbstractClasses.ConcursoAbstract;
import br.ufrn.myway.Model.AbstractClasses.ConcursoFactory;
import br.ufrn.myway.Model.Entities.Banca;
import br.ufrn.myway.Model.Entities.ConcursoPoliciaCivil;
import br.ufrn.myway.Model.Entities.ConcursoProfessor;
import br.ufrn.myway.Model.Entities.Prova;
import br.ufrn.myway.Model.Enums.NivelConcurso;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("professorFactory")
public class ConcursoProfessorFactory implements ConcursoFactory {

    @Override
    public ConcursoAbstract createConcurso(String edital, NivelConcurso nivel, Banca banca,
                                           Prova prova) {
        ConcursoProfessor concurso = new ConcursoProfessor();
        concurso.setEdital(edital);
        concurso.setNivelConcurso(nivel);
        concurso.setBancaConcurso(createBanca(banca));
        concurso.setProva(createProva(prova));
        return concurso;
    }

    @Override
    public Banca createBanca(Banca banca) {
        banca.setName(banca.getName());
        return banca;
    }

    @Override
    public Prova createProva(Prova prova) {
        prova.setNome(prova.getNome());
        return prova;
    }

}
