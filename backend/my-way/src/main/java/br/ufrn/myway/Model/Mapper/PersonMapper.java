package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.Person;
import br.ufrn.myway.Model.DTO.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person toEntity(PersonDTO dto);
    PersonDTO toDto(Person entity);
}
