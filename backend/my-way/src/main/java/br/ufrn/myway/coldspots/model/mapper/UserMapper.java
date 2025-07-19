package br.ufrn.myway.coldspots.model.mapper;

import br.ufrn.myway.coldspots.model.DTO.UserDTO;
import br.ufrn.myway.coldspots.model.entities.User;

import org.mapstruct.Mapper; 

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User toEntity(UserDTO dto);
    UserDTO toDto(User user);

}
