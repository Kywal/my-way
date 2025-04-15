package Controller;

import DTO.PessoaDTO;
import Entities.PessoaClasse;
import Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Pessoa")

public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaClasse> criarPessoa(@RequestBody PessoaDTO novaPessoa){
        PessoaClasse pessoaClasse = pessoaService.criarNovaPessoa(novaPessoa);
        return new ResponseEntity<>(pessoaClasse, HttpStatus.CREATED);
    }
    @GetMapping
    public List<PessoaClasse> retornarPessoas(){
        return pessoaService.retornarPessoas();
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<String> apagarPessoa(@PathVariable String pessoaEmail){
        pessoaService.deletarPessoaPorEmail(pessoaEmail);
        return ResponseEntity.ok("Pessoa apagada com sucesso");
    }
}
