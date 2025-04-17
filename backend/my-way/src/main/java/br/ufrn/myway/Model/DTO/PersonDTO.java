package br.ufrn.myway.Model.DTO;

import java.time.LocalDate;

import br.ufrn.myway.Model.Enums.GenderPerson;

public record PersonDTO(UserDTO user, String name, LocalDate birthDate, GenderPerson gender, String historyDescription, String country, String region) {
}
