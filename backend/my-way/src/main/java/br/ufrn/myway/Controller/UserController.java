package br.ufrn.myway.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.ufrn.myway.Service.AuthService.AuthorizationDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.myway.Model.DTO.LoginDTO;
import br.ufrn.myway.Model.DTO.UserDTO;
import br.ufrn.myway.Model.Entities.User;
import br.ufrn.myway.Model.Enums.Roles;
import br.ufrn.myway.Model.Mapper.UserMapper;
import br.ufrn.myway.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    public UserController() {
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String codedBasicAuth) {
        String[] credentials = AuthorizationDecoder.decodeBasicAuth(codedBasicAuth);
        String email = credentials[0];
        String password = credentials[1];

        User user = userService.loginByEmail(email, password);
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setRole(Roles.USER);
        user = userService.save(user);
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/list")
    public List<UserDTO> ListUser() {
        return userService.list().stream().map(u -> userMapper.toDto(u)).collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userMapper.toDto(userService.findById(id)), HttpStatus.CREATED);
    }
}
