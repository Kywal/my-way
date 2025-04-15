package Entities;

import DTO.PessoaDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_pessoa")
public class PessoaClasse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID idUsuario;

    private String pessoaNome;

    private int pessoaIdade;

    private GeneroPessoa pessoaGenero;

    private String historyDescription;

    private String pessoaPais;

    private String pessoaEstado;

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPessoaNome() {
        return pessoaNome;
    }

    public void setPessoaNome(String pessoaNome) {
        this.pessoaNome = pessoaNome;
    }

    public int getPessoaIdade() {
        return pessoaIdade;
    }

    public void setPessoaIdade(int pessoaIdade) {
        this.pessoaIdade = pessoaIdade;
    }

    public GeneroPessoa getPessoaGenero() {
        return pessoaGenero;
    }

    public void setPessoaGenero(GeneroPessoa pessoaGenero) {
        this.pessoaGenero = pessoaGenero;
    }

    public String getHistoryDescription() {
        return historyDescription;
    }

    public void setHistoryDescription(String historyDescription) {
        this.historyDescription = historyDescription;
    }

    public String getPessoaPais() {
        return pessoaPais;
    }

    public void setPessoaPais(String pessoaPais) {
        this.pessoaPais = pessoaPais;
    }

    public String getPessoaEstado() {
        return pessoaEstado;
    }

    public void setPessoaEstado(String pessoaEstado) {
        this.pessoaEstado = pessoaEstado;
    }

    public PessoaClasse(PessoaDTO pessoaDTO){
        this.pessoaNome = pessoaDTO.pessoaNome();
        this.pessoaIdade = pessoaDTO.pessoaIdade();
        this.pessoaGenero = pessoaDTO.pessoaGenero();
        this.historyDescription = pessoaDTO.historyDescription();
        this.pessoaPais = pessoaDTO.pessoaPais();
        this.pessoaEstado = pessoaDTO.pessoaEstado();
    }
}
