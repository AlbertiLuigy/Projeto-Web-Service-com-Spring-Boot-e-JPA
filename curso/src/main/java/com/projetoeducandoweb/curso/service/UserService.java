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

    public User insert(User obj){ // Método para inserir um usuário
        return repository.save(obj); // Retornando o usuário inserido

    }

    public void delete(Long id){ // Método para deletar um usuário pelo ID
        repository.deleteById(id); // Deletando o usuário pelo ID
    }

    public User update(Long id, User obj){ //Método para atualizar um usuário pelo ID
        User entity = repository.getReferenceById(id); // Retornando o usuário pelo ID
        updateData(entity, obj); // Chamando o método updateData para atualizar os dados do usuário
                return repository.save(entity); // Retornando o usuário atualizado
            }
        
    private void updateData(User entity, User obj) {
        entity.setName(obj.getName()); // Atualizando o nome do usuário
        entity.setEmail(obj.getEmail()); // Atualizando o email do usuário
        entity.setPhone(obj.getPhone()); // Atualizando o telefone do usuário
    }
}