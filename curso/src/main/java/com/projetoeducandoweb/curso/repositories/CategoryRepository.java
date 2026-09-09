package com.projetoeducandoweb.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoeducandoweb.curso.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
