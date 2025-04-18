package br.ufrn.myway.Service;
 
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Repository.UserRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List; 

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List <User> list(){
        return userRepository.list();
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User findById(Long id) {
        return userRepository.getById(id);
    }

}
