package br.ufrn.myway.Service;
 
import br.ufrn.myway.Model.Person;
import br.ufrn.myway.Repository.PersonRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List; 

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> list(){

        return personRepository.list();
    }

    public void delete(Long id) {
        personRepository.delete(id);
    }

}
