package br.ufrn.myway.model.mapper;

import br.ufrn.myway.model.DTO.UserDTO;
import br.ufrn.myway.model.entities.User;

import org.mapstruct.Mapper; 

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User toEntity(UserDTO dto);
    UserDTO toDto(User user);

}
