package Service;

import DTO.PessoaDTO;
import Entities.Person;
import Repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    public void salvarModificacoesPessoa(Person novaPessoa){
        this.pessoaRepository.save(novaPessoa);
    }
    public Person criarNovaPessoa(PessoaDTO novaPessoa){
        Person person = new Person(novaPessoa);
        this.salvarModificacoesPessoa(person);
        return person;
    }

    public List <Person> retornarPessoas(){
        return this.pessoaRepository.findAll();
    }
    public Person atualizarPessoa(String pessoaEmail, PessoaDTO atualizacao){
        Person person = pessoaRepository.findByPessoaEmail(pessoaEmail).orElseThrow(()->
                new RuntimeException("Pessoa não encontrada com o email: " + pessoaEmail));

        BeanUtils.copyProperties(atualizacao, person, getNullPropertyNames(atualizacao));

        return pessoaRepository.save(person);
    }

    public void deletarPessoaPorEmail(String pessoaEmail) {
        Person pessoa = pessoaRepository.findByPessoaEmail(pessoaEmail)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o email: " + pessoaEmail));
        pessoaRepository.delete(pessoa);
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
