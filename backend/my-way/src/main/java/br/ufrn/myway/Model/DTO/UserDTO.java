package br.ufrn.myway.Model.DTO;


public record UserDTO(String email, String password, int tokens, PersonDTO person) {
}
