// package br.ufrn.myway.Controller;

// import br.ufrn.myway.Model.DTO.PersonDTO;
// import br.ufrn.myway.Model.Entities.Person;
// import br.ufrn.myway.Model.Mapper.PersonMapper;
// import br.ufrn.myway.Service.PersonService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.UUID;

// @RestController
// @RequestMapping("/Person")

// public class PersonController {

//     @Autowired
//     PersonService personService;

//     @Autowired
//     private PersonMapper personMapper;

//     @PostMapping
//     public ResponseEntity<Person> save(@RequestBody PersonDTO personDto){
//         Person person = personMapper.toEntity(personDto);
//         return new ResponseEntity<>(person, HttpStatus.CREATED);
//     }

//     // @GetMapping
//     // public List<Person> ListPerson(){
//     //     return personService.ListPersons();
//     // }

//     // @DeleteMapping("/{id}")
//     // public ResponseEntity<String> apagarPessoa(@PathVariable UUID idPerson){
//     //     personService.deletePersonById(idPerson);
//     //     return ResponseEntity.ok("Pessoa apagada com sucesso");
//     // }
// }
