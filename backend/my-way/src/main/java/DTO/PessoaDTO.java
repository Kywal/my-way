package DTO;

import Entities.GeneroPessoa;

public record PessoaDTO(String pessoaNome, int pessoaIdade, GeneroPessoa pessoaGenero, String historyDescription, String pessoaPais, String pessoaEstado) {
}
