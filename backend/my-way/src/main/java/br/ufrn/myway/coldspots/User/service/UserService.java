package br.ufrn.myway.coldspots.User.service;

import java.util.List;

import br.ufrn.myway.coldspots.Exceptions.BusinessException;
import br.ufrn.myway.coldspots.Mission.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufrn.myway.coldspots.model.entities.User;
import br.ufrn.myway.coldspots.model.enums.ErrorMessageUtils;
import br.ufrn.myway.coldspots.User.repository.UserRepository;

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
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User findById(Long id) {
        User user = userRepository.getById(id);
        if(user == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("User"));
        }
        return user;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_NOT_FOUND.getMessage("User"));
        }
        return userRepository.findByEmail(email);
    }

    public User loginByEmail(String email, String password) {

        UsernamePasswordAuthenticationToken authenticationRequest
                = UsernamePasswordAuthenticationToken.unauthenticated(email, password);

        Authentication authenticationResponse
                = this.authenticationManager.authenticate(authenticationRequest);

        UserDetails userDetails = (UserDetails) authenticationResponse.getPrincipal();
        User user = findByEmail(userDetails.getUsername());

        if(user == null) {
            throw new BusinessException(HttpStatus.NOT_FOUND, ErrorMessageUtils.ERROR_LOGIN.getMessage());
        }

        missionService.assignDailyMissionIfNeeded(user);

        return user;
    }
}
