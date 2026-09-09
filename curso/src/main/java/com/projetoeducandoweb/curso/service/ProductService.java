package com.projetoeducandoweb.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoeducandoweb.curso.entities.Product;
import com.projetoeducandoweb.curso.repositories.ProductRepository;

@Service
public class ProductService { //

    @Autowired // Injeção de dependência
    // Injetando o objeto ProductRepository na variável repository
    private ProductRepository repository;
    
    public List<Product> findAll(){ // Método para buscar todos os produtos
        return repository.findAll(); // Retornando todos os produtos
    }

    public Product findById(Long id){ // Método para buscar um produto pelo ID
        Optional<Product> obj = repository.findById(id); // Retornando o produto pelo ID
        return obj.get(); // Retornando o produto
    }  
}