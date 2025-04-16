package Service;

import DTO.PersonDTO;
import Entities.Person;
import Repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PessoaService {
    @Autowired
    private PersonRepository personRepository;

    public PessoaService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public void saveChangesPerson(Person novaPessoa){
        this.personRepository.save(novaPessoa);
    }
    public Person createNewPerson(PersonDTO novaPessoa){
        Person person = new Person(novaPessoa);
        this.saveChangesPerson(person);
        return person;
    }

    public List <Person> ListPersons(){
        return this.personRepository.findAll();
    }
    public Person updatePerson(UUID idPerson, PersonDTO update){
        Person person = personRepository.findById(idPerson).orElseThrow(()->
                new RuntimeException("Person not found by the ID: " + idPerson));

        BeanUtils.copyProperties(update, person, getNullPropertyNames(update));

        return personRepository.save(person);
    }

    public void deletePersonById(UUID idPerson) {
        Person pessoa = personRepository.findById(idPerson)
                .orElseThrow(() -> new RuntimeException("Person not found by ID: " + idPerson));
        personRepository.delete(pessoa);
    }

    private String[] getNullPropertyNames(Object source) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .map(pd -> {
                    try {
                        Object value = pd.getReadMethod().invoke(source);
                        return value == null ? pd.getName() : null;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toArray(String[]::new);
    }
}
