package com.projetoeducandoweb.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoeducandoweb.curso.entities.Order;
import com.projetoeducandoweb.curso.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResources {
    
    @Autowired
    private OrderService orderService; // Injetando o objeto OrderService na variável orderService
    
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){ // Método para buscar todos os pedidos
        List<Order> list = orderService.findAll(); // Chamando o método findAll do serviço orderService
        return ResponseEntity.ok().body(list); // Retornando todos os pedidos
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){ // Método para buscar um pedido pelo ID
        Order obj = orderService.findById(id); // Chamando o método findById do serviço com o ID do pedido
        return ResponseEntity.ok().body(obj); // Retornando o pedido
    }
}
