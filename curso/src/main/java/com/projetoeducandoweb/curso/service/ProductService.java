package com.projetoeducandoweb.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoeducandoweb.curso.entities.Product;
import com.projetoeducandoweb.curso.repositories.ProductRepository;
import com.projetoeducandoweb.curso.service.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    
    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }  
}
