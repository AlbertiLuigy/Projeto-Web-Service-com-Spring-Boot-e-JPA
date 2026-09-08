package com.projetoeducandoweb.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoeducandoweb.curso.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
