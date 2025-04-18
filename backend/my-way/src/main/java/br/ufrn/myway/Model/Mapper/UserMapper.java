package br.ufrn.myway.Model.Mapper;

import br.ufrn.myway.Model.DTO.UserDTO;
import br.ufrn.myway.Model.Entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface UserMapper {

    User toEntity(UserDTO dto);

    UserDTO toDto(User user);
}
