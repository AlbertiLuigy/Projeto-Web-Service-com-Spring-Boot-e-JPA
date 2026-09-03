package com.projetoeducandoweb.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoeducandoweb.curso.entities.User;
import com.projetoeducandoweb.curso.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userRepository.findAll();
        return ResponseEntity.ok().body(list);
    }
}
