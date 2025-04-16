package Controller;

import DTO.PersonDTO;
import Entities.Person;
import Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Person")

public class PersonController {
    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Person> criarPessoa(@RequestBody PersonDTO novaPessoa){
        Person person = pessoaService.createNewPerson(novaPessoa);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Person> ListPerson(){
        return pessoaService.ListPersons();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagarPessoa(@PathVariable UUID idPerson){
        pessoaService.deletePersonById(idPerson);
        return ResponseEntity.ok("Pessoa apagada com sucesso");
    }
}
