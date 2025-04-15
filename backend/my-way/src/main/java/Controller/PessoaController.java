package Controller;

import DTO.PessoaDTO;
import Entities.Person;
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
    public ResponseEntity<Person> criarPessoa(@RequestBody PessoaDTO novaPessoa){
        Person person = pessoaService.criarNovaPessoa(novaPessoa);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Person> retornarPessoas(){
        return pessoaService.retornarPessoas();
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<String> apagarPessoa(@PathVariable String pessoaEmail){
        pessoaService.deletarPessoaPorEmail(pessoaEmail);
        return ResponseEntity.ok("Pessoa apagada com sucesso");
    }
}
