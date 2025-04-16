package Service;

import DTO.UserDTO;
import Entities.User;
import Repository.UserRepository;
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

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public List <User> ListUsers(){
        return this.userRepository.findAll();
    }
    public User createNewUser(UserDTO userDTO) {
        User user = new User(
                userDTO.personDTO(),
                userDTO.email(),
                userDTO.password(),
                userDTO.tokens()
        );
        return userRepository.save(user);
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
        userRepository.delete(user);
    }

    public User updateUser (UUID userId, UserDTO updateUser){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));
        BeanUtils.copyProperties(updateUser, user, getNullPropertyNames(updateUser));

        return userRepository.save(user);
    }
    private String[] getNullPropertyNames(Object source) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .map(pd -> {
                    try {
                        Object value = pd.getReadMethod().invoke(source);
                        return value == null ? pd.getName() : null;
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toArray(String[]::new);
    }
}
