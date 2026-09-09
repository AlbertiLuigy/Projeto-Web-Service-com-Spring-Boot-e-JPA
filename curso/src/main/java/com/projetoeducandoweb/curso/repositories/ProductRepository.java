package com.projetoeducandoweb.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoeducandoweb.curso.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
