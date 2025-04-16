package br.ufrn.myway.Model.DTO;

import br.ufrn.myway.Model.Enums.GenderPerson;

public record PersonDTO(String name, int age, GenderPerson gender, String historyDescription, String country, String region) {
}
