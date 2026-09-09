package com.projetoeducandoweb.curso.entities.enums;

public enum OrderStaus {
    WATTING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    private OrderStaus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStaus valueOf(int code) {
        for (OrderStaus value : OrderStaus.values()) { //Pecorre todos os valores do enum OrderStatus
            if (value.getCode() == code) { //Se o código do valor atual for igual ao código passado como parâmetro, retorna o valor correspondente
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code"); //Se nenhum valor do enum corresponder ao código passado como parâmetro, lança uma exceção IllegalArgumentException com a mensagem "Invalid OrderStatus code"
    }
}
