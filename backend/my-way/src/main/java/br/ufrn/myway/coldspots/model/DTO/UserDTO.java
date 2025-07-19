package br.ufrn.myway.coldspots.model.DTO;

import br.ufrn.myway.coldspots.model.enums.Roles;

public record UserDTO(Long id, String email, String password, int tokens, Roles role, PersonDTO person) {

}
