package com.projetoeducandoweb.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoeducandoweb.curso.entities.Category;
import com.projetoeducandoweb.curso.service.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResources {
    
    @Autowired
    private CategoryService categoryService; // Injetando o objeto CategoryService na variável categoryService
    
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){ // Método para buscar todas as categorias
        List<Category> list = categoryService.findAll(); // Chamando o método findAll do serviço categoryService
        return ResponseEntity.ok().body(list); // Retornando todas as categorias
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){ // Método para buscar uma categoria pelo ID
        Category obj = categoryService.findById(id); // Chamando o método findById do serviço com o ID da categoria
        return ResponseEntity.ok().body(obj); // Retornando a categoria
    }
}
