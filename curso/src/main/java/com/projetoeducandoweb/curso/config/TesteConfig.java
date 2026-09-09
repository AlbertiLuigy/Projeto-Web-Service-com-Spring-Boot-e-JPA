package com.projetoeducandoweb.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetoeducandoweb.curso.entities.Order;
import com.projetoeducandoweb.curso.entities.User;
import com.projetoeducandoweb.curso.entities.enums.OrderStaus;
import com.projetoeducandoweb.curso.repositories.OrderRepository;
import com.projetoeducandoweb.curso.repositories.UserRepository;

@Configuration
@Profile("teste")
public class TesteConfig implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria", "maria@example.com", "987654321", "123456");
        User u2 = new User(null, "João", "joao@example.com", "987654321", "123456");
        
        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, Instant.parse("2023-01-01T00:00:00Z"), OrderStaus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2023-01-02T00:00:00Z"), OrderStaus.CANCELED, u2);
        Order o3 = new Order(null, Instant.parse("2023-01-03T00:00:00Z"), OrderStaus.SHIPPED, u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }

}
