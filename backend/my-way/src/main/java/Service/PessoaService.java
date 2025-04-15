package Service;

import DTO.PessoaDTO;
import Entities.PessoaClasse;
import Repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public void salvarModificacoesPessoa(PessoaClasse novaPessoa){
        this.pessoaRepository.save(novaPessoa);
    }
    public PessoaClasse criarNovaPessoa(PessoaDTO novaPessoa){
        PessoaClasse pessoaClasse = new PessoaClasse(novaPessoa);
        this.salvarModificacoesPessoa(pessoaClasse);
        return pessoaClasse;
    }
    public List <PessoaClasse> retornarPessoas(){
        return this.pessoaRepository.findAll();
    }
    public PessoaClasse atualizarPessoa(String pessoaEmail, PessoaDTO atualizacao){
        PessoaClasse pessoaClasse = pessoaRepository.findByPessoaEmail(pessoaEmail).orElseThrow(()->
                new RuntimeException("Pessoa não encontrada com o email: " + pessoaEmail));

        BeanUtils.copyProperties(atualizacao,pessoaClasse, getNullPropertyNames(atualizacao));

        return pessoaRepository.save(pessoaClasse);
    }
    public void deletarPessoaPorEmail(String pessoaEmail) {
        PessoaClasse pessoa = pessoaRepository.findByPessoaEmail(pessoaEmail)
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
