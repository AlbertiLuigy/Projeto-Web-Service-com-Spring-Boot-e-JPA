package com.projetoeducandoweb.curso.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetoeducandoweb.curso.entities.enums.OrderStaus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStaus;

    @ManyToOne //Um pedido tem um cliente, mas um cliente pode ter vários pedidos
    @JoinColumn(name = "client_id")
    private User client;
    
    public Order(){
    }

    public Order(Long id, Instant moment, OrderStaus orderStaus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStaus(orderStaus); //função que converte o enum para Integer
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStaus getOrderStaus() {
        return OrderStaus.valueOf(orderStaus); //função que converte o Integer para enum
    }

    public void setOrderStaus(OrderStaus orderStaus) {
        if(orderStaus != null){
        this.orderStaus = orderStaus.getCode();} //função que converte o enum para Integer
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
