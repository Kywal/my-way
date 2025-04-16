package br.ufrn.myway.Service;

import br.ufrn.myway.Model.DTO.UserDTO;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }
    public List <User> list(){
        return userRepository.findAll();
    }
    public void delete(Long id) {
    }


}
