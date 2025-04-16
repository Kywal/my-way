package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.PersonDTO;
import br.ufrn.myway.Model.Entities.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO dto);
    PersonDTO toDto(Person entity);
}
