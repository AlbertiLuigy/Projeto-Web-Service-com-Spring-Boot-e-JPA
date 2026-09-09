package com.projetoeducandoweb.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoeducandoweb.curso.entities.Category;
import com.projetoeducandoweb.curso.repositories.CategoryRepository;

@Service
public class CategoryService { //

    @Autowired // Injeção de dependência
    // Injetando o objeto CategoryRepository na variável repository
    private CategoryRepository repository;
    
    public List<Category> findAll(){ // Método para buscar todas as categorias
        return repository.findAll(); // Retornando todas as categorias
    }

    public Category findById(Long id){ // Método para buscar uma categoria pelo ID
        Optional<Category> obj = repository.findById(id); // Retornando a categoria pelo ID
        return obj.get(); // Retornando a categoria
    }  
}