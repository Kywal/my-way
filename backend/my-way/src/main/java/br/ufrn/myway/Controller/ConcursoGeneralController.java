package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.Entities.ConcursoGeneral;
import br.ufrn.myway.Service.ConcursoGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concurso")
public class ConcursoGeneralController {
    @Autowired
    ConcursoGeneralService concursoGeneralService;

    @PostMapping("/criarConcursoGeneral")
    public void criarConcursoGeneral(@RequestBody ConcursoGeneral concursoGeneral) {
        concursoGeneralService.save(concursoGeneral);
    }
}
