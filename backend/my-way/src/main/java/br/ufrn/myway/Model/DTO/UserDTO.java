package br.ufrn.myway.Model.DTO;

public record UserDTO(PersonDTO personDTO, String email, String password, int tokens) {
}
