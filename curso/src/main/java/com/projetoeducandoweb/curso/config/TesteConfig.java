package com.projetoeducandoweb.curso.config;

import java.time.Instant;
import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetoeducandoweb.curso.entities.Category;
import com.projetoeducandoweb.curso.entities.Order;
import com.projetoeducandoweb.curso.entities.OrderItem;
import com.projetoeducandoweb.curso.entities.Product;
import com.projetoeducandoweb.curso.entities.User;
import com.projetoeducandoweb.curso.entities.enums.OrderStaus;
import com.projetoeducandoweb.curso.repositories.CategoryRepository;
import com.projetoeducandoweb.curso.repositories.OrderItemRepository;
import com.projetoeducandoweb.curso.repositories.OrderRepository;
import com.projetoeducandoweb.curso.repositories.ProductRepository;
import com.projetoeducandoweb.curso.repositories.UserRepository;

@Configuration
@Profile("teste")
public class TesteConfig implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Product pdc1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product pdc2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product pdc3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");

        productRepository.saveAll(Arrays.asList(pdc1, pdc2, pdc3));

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));


        pdc1.getCategories().add(cat2);
        pdc2.getCategories().add(cat1);
        pdc3.getCategories().add(cat3);
        pdc3.getCategories().add(cat1);

        productRepository.saveAll(Arrays.asList(pdc1, pdc2, pdc3));

        User u1 = new User(null, "Maria", "maria@example.com", "987654321", "123456");
        User u2 = new User(null, "João", "joao@example.com", "987654321", "123456");
        
        userRepository.saveAll(Arrays.asList(u1, u2));

        Order o1 = new Order(null, Instant.parse("2023-01-01T00:00:00Z"), OrderStaus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2023-01-02T00:00:00Z"), OrderStaus.CANCELED, u2);
        Order o3 = new Order(null, Instant.parse("2023-01-03T00:00:00Z"), OrderStaus.SHIPPED, u1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, pdc1, 2, pdc1.getPrice());
        OrderItem oi2 = new OrderItem(o1, pdc3, 1, pdc3.getPrice());
        OrderItem oi3 = new OrderItem(o2, pdc3, 2, pdc3.getPrice());
        OrderItem oi4 = new OrderItem(o3, pdc2, 1, pdc2.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
    }

}
