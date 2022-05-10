package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;

import java.util.UUID;

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
