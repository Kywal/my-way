package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Entities.Person;

public record UserDTO(Person person, String email, String password, int tokens) {
}
