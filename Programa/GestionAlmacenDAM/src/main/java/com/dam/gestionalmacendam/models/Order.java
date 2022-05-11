package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;



@AllArgsConstructor
@Data
public class Order {
    private String OIC = UUID.randomUUID().toString();
    private ObjectProperty<Object> customer;
    private DoubleProperty price;
    private ObjectProperty methodPay;

    public Order() {
    }

    public Order(Object customer, double price, Object methodPay) {
        this.OIC = OIC;
        this.customer = new SimpleObjectProperty(customer);
        this.price = new SimpleDoubleProperty(price);
        this.methodPay = new SimpleObjectProperty(methodPay);
    }

    public Order(String OIC, Object customer, double price, Object methodPay) {
        this.OIC = OIC;
        this.customer = new SimpleObjectProperty(customer);
        this.price = new SimpleDoubleProperty(price);
        this.methodPay = new SimpleObjectProperty(methodPay);
    }

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
