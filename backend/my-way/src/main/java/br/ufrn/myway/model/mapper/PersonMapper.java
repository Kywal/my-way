package br.ufrn.myway.model.mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.model.DTO.PersonDTO;
import br.ufrn.myway.model.entities.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO dto);
    PersonDTO toDto(Person entity);
}
