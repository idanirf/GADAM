package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;



@AllArgsConstructor
@Data
public class Order {
    private StringProperty OIC ;
    private StringProperty customer;
    private DoubleProperty price;
    private ObjectProperty<Pay> methodPay;

    public Order() {
    }

    public Order(String customer, double price, Object methodPay) {
        this.OIC = new SimpleStringProperty(UUID.randomUUID().toString());
        this.customer = new SimpleStringProperty(customer);
        this.price = new SimpleDoubleProperty(price);
        this.methodPay = new SimpleObjectProperty(methodPay);
    }

    public Order(String OIC, String customer, double price, Object methodPay) {
        this.OIC = new SimpleStringProperty(OIC);
        this.customer = new SimpleStringProperty(customer);
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
