package br.ufrn.myway.model.DTO;

import br.ufrn.myway.model.Enums.Roles;

public record UserDTO(Long id, String email, String password, int tokens, Roles role, PersonDTO person) {

}
