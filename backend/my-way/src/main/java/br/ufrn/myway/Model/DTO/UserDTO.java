package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Enums.Roles;

public record UserDTO(String email, String password, int tokens, Roles role, PersonDTO person) {

}
