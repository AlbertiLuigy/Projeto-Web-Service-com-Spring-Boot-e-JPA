package com.projetoeducandoweb.curso.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoeducandoweb.curso.entities.User;

@RestController // Controlador RESTful
@RequestMapping(value = "/users") // Mapeia a URL para o recurso
public class UserResources {
    
    @GetMapping
    public ResponseEntity<User> findAll(){
        User u = new User(1L, "Maria", "maria@maria.com", "988888888", "123456" );
        return ResponseEntity.ok().body(u);
    }
}
