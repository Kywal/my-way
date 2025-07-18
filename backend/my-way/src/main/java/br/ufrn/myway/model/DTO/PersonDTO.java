package br.ufrn.myway.model.DTO;

import java.time.LocalDate;

import br.ufrn.myway.model.enums.GenderPerson;

public record PersonDTO(String name, LocalDate birthDate, GenderPerson gender, String historyDescription, String country, String region) {
}
