package com.projetoeducandoweb.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoeducandoweb.curso.entities.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
}
