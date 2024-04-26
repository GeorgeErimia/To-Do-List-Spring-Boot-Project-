package com.georgeerimia.todoproject.Service.impl;

import com.georgeerimia.todoproject.Exception.TodoAPIException;
import com.georgeerimia.todoproject.Model.Role;
import com.georgeerimia.todoproject.Model.User;
import com.georgeerimia.todoproject.Repository.RoleRepository;
import com.georgeerimia.todoproject.Repository.UserRepository;
import com.georgeerimia.todoproject.Service.AuthService;
import com.georgeerimia.todoproject.dtos.LoginDTO;
import com.georgeerimia.todoproject.dtos.RegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterDTO registerDTO) {

        // check if username is already taken
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        // check if email is already taken
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email is already taken!");
        }

        // create new user
        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));


        // set user roles
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);

        // save user
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public String login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User logged in successfully!";
    }
}
