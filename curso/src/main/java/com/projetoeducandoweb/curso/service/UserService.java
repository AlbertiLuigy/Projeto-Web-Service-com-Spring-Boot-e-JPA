package com.projetoeducandoweb.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoeducandoweb.curso.entities.User;
import com.projetoeducandoweb.curso.repositories.UserRepository;

@Service
public class UserService {

    @Autowired // Injeção de dependência
    // Injetando o objeto UserRepository na variável repository
    private UserRepository repository;
    
    public List<User> findAll(){ // Método para buscar todos os usuários
        return repository.findAll(); // Retornando todos os usuários
    }

    public User findById(Long id){ // Método para buscar um usuário pelo ID
        Optional<User> obj = repository.findById(id); // Retornando o usuário pelo ID
        return obj.get(); // Retornando o usuário
    }

    
}