package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.UserDTO;
import br.ufrn.myway.Model.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);
    UserDTO toDto(User user);

}
