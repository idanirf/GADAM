package com.dam.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Order {
    private final UUID OIC;
    private Customer customer;
    private Double price;
    private Pay methodPay;

    @Override
    public String toString() {
        return "Order{" +
                "OIC=" + OIC +
                ", customer=" + customer +
                ", price=" + price +
                ", methodPay=" + methodPay +
                '}';
    }
}
