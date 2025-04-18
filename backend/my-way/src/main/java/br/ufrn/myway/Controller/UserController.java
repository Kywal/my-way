package br.ufrn.myway.Controller;

import br.ufrn.myway.Model.User;
import br.ufrn.myway.Model.DTO.LoginDTO;
import br.ufrn.myway.Model.DTO.UserDTO;
import br.ufrn.myway.Model.Mapper.UserMapper;
import br.ufrn.myway.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List; 
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginRequest) {

        UsernamePasswordAuthenticationToken authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());

        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);

        return new ResponseEntity<>(userMapper.toDto((User) authenticationResponse.getPrincipal()), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userService.save(user);
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    @GetMapping("/list")
    public List<UserDTO> ListUser(){
        return userService.list().stream().map(u->userMapper.toDto(u)).collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userMapper.toDto(userService.findById(id)), HttpStatus.CREATED);
    }
}