package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


@Data
public class Order {
    private String OIC= UUID.randomUUID().toString();
    private StringProperty customer;
    private DoubleProperty price;
    private ObjectProperty<Pay> methodPay;


    public Order(String customer, double price, Pay methodPay) {

       // lo tengo que poner aqui porque tiene que ser obserbable para pasarlo a la tabla

        this.customer = new SimpleStringProperty(customer);
        this.price = new SimpleDoubleProperty(price);
        this.methodPay = new SimpleObjectProperty(methodPay);
    }

    public Order(String OIC, String customer, double price, Pay methodPay) {
        this.OIC=  OIC;
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


    public DoubleProperty priceProperty() {
        return price;
    }


}
