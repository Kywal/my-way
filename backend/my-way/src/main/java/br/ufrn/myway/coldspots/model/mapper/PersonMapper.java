package br.ufrn.myway.coldspots.model.mapper;

import org.mapstruct.Mapper;

import br.ufrn.myway.coldspots.model.DTO.PersonDTO;
import br.ufrn.myway.coldspots.model.entities.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO dto);
    PersonDTO toDto(Person entity);
}
