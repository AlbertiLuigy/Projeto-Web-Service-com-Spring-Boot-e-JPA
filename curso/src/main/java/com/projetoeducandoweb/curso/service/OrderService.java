package com.projetoeducandoweb.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoeducandoweb.curso.entities.Order;
import com.projetoeducandoweb.curso.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired // Injeção de dependência
    // Injetando o objeto OrderRepository na variável repository
    private OrderRepository repository;
    
    public List<Order> findAll(){ // Método para buscar todos os pedidos
        return repository.findAll(); // Retornando todos os pedidos
    }

    public Order findById(Long id){ // Método para buscar um pedido pelo ID
        Optional<Order> obj = repository.findById(id); // Retornando o pedido pelo ID
        return obj.get(); // Retornando o pedido
    }

    
}