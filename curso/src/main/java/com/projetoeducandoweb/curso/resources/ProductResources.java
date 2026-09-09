package com.projetoeducandoweb.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoeducandoweb.curso.entities.Product;
import com.projetoeducandoweb.curso.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResources {
    
    @Autowired
    private ProductService productServiceService; // Injetando o objeto ProductService na variável productServiceService
    
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){ // Método para buscar todos os produtos
        List<Product> list = productServiceService.findAll(); // Chamando o método findAll do serviço productServiceService
        return ResponseEntity.ok().body(list); // Retornando todos os produtos
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){ // Método para buscar um produto pelo ID
        Product obj = productServiceService.findById(id); // Chamando o método findById do serviço com o ID do produto
        return ResponseEntity.ok().body(obj); // Retornando o produto
    }
}
