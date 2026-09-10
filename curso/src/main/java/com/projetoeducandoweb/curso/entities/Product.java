package com.projetoeducandoweb.curso.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany 
    @JoinTable(name = "tb_product_category", 
    joinColumns = @JoinColumn (name = "product_id"), 
    inverseJoinColumns = @JoinColumn (name = "category_id")) //criando a tabela de associação entre produto e categoria, com as chaves estrangeiras product_id e category_id
    private Set<Category> categories = new HashSet<>(); //fazendo a associação entre produto e categoria, um produto pode ter várias categorias

    @OneToMany(mappedBy = "id.product") //um produto pode ter vários itens de pedido, mas um item de pedido pertence a um produto
    private Set<OrderItem> items = new HashSet<>(); //fazendo a associação entre produto e item de pedido, um produto pode ter vários itens de pedido
    
    
    public Product() {
    }

    public Product(Long id, String nome, String description, Double price, String imgUrl) {
        this.id = id;
        this.nome = nome;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }
    
    @JsonIgnore //para não entrar em loop infinito, pois o produto tem uma lista de pedidos e o pedido tem uma lista de produtos
    public Set<Order> getOrders() { //retorna os pedidos que contém o produto
        Set<Order> set = new HashSet<>();
        for (OrderItem x : items) {
            set.add(x.getOrder());
        }
        return set;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
