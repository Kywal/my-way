package br.ufrn.myway.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.ErrorMessageUtils;
import br.ufrn.myway.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager = null;

    @Autowired
    private MissionService missionService;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> list() {
        return userRepository.list();
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("User")));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("User")));
    }

    public User loginByEmail(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationRequest
                = UsernamePasswordAuthenticationToken.unauthenticated(email, password);

        Authentication authenticationResponse
                = this.authenticationManager.authenticate(authenticationRequest);

        UserDetails userDetails = (UserDetails) authenticationResponse.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

        missionService.assignDailyMissionIfNeeded(user);

        return user;
    }
}
