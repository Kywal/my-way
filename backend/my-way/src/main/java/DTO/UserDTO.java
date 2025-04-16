package DTO;

public record UserDTO(PersonDTO personDTO, String email, String password, int tokens) {
}
