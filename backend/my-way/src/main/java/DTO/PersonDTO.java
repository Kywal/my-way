package DTO;

import Entities.GenderPerson;

public record PersonDTO(String name, int age, GenderPerson gender, String historyDescription, String country, String region) {
}
