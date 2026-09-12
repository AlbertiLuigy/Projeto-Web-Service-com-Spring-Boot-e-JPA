package com.projetoeducandoweb.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoeducandoweb.curso.entities.Order;
import com.projetoeducandoweb.curso.repositories.OrderRepository;
import com.projetoeducandoweb.curso.service.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
