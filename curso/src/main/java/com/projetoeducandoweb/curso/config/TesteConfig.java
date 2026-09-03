package com.projetoeducandoweb.curso.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetoeducandoweb.curso.entities.User;
import com.projetoeducandoweb.curso.repositories.UserRepository;

@Configuration
@Profile("teste")
public class TesteConfig implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria", "maria@example.com", "987654321", "123456");
        User u2 = new User(null, "João", "joao@example.com", "987654321", "123456");
        
        userRepository.saveAll(Arrays.asList(u1, u2));
    }

}
