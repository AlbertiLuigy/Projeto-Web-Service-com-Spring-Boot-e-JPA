package com.projetoeducandoweb.curso.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoeducandoweb.curso.entities.User;
import com.projetoeducandoweb.curso.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
    
    @Autowired
    private UserService userService; // Injetando o objeto UserService na variável userService
    
    @GetMapping
    public ResponseEntity<List<User>> findAll(){ // Método para buscar todos os usuários
        List<User> list = userService.findAll(); // Chamando o método findAll do serviço
        return ResponseEntity.ok().body(list); // Retornando todos os usuários
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){ // Método para buscar um usuário pelo ID
        User obj = userService.findById(id); // Chamando o método findById do serviço com o ID do usuário
        return ResponseEntity.ok().body(obj); // Retornando o usuário
    }

    @PostMapping 
    public ResponseEntity<User> insert(@RequestBody User obj){ // Método para inserir um usuário
        obj = userService.insert(obj); // Chamando o método insert do serviço com o objeto do usuário

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // Criando a URI do usuário inserido
        return ResponseEntity.created(uri).body(obj); // Retornando o usuário inserido
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ // Método para deletar um usuário pelo ID
        userService.delete(id); // Chamando o método delete do serviço com o ID do usuário
        return ResponseEntity.noContent().build(); // Retornando a resposta sem conteúdo
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){ // Método para atualizar um usuário pelo ID
        obj = userService.update(id, obj); // Chamando o método update do serviço com o ID do usuário e o objeto do usuário
        return ResponseEntity.ok().body(obj); // Retornando o usuário atualizado
    }
}
