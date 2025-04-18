package br.ufrn.myway.Model.Mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.Model.DTO.PersonDTO;
import br.ufrn.myway.Model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO dto);
    PersonDTO toDto(Person entity);
}
